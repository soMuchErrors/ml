package com.rainbow.machinelearning;

import com.alibaba.fastjson.JSONObject;
import com.rainbow.machinelearning.dto.AppDto;
import com.rainbow.machinelearning.dto.CheckIn;
import com.rainbow.machinelearning.dto.CheckInObject;
import com.rainbow.machinelearning.model.User;
import com.rainbow.machinelearning.service.UserService;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.sql.SQLContext;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import scala.Tuple2;

import java.util.*;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MachineLearningApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private JavaSparkContext javaSparkContext;

	@Test
	public void contextLoads() {
		Map<String,Object> result = new HashMap();
		User user = new User();
		user.setAge(10);
		user.setName("Tom");
		userService.insert(user);
		result.put("code","200");
		result.put("body",user);
		result.put("msg","success");
		System.out.println(JSONObject.toJSONString(result));
	}

	@Test
	public void checkInRdd(){
		JavaRDD<CheckIn> javaRDD = javaSparkContext.textFile("/Users/mc/Downloads/book2-master/2rd_data/ch02/Gowalla_totalCheckins.txt").map(line -> line.split("\t")).mapPartitions(
		 		partition -> {
					List<CheckIn> checkIns = Lists.newArrayList();

					while(partition.hasNext()){
						String[] s = partition.next();
						checkIns.add(new CheckIn(s[0], s[1].substring(0,10), Double.valueOf(s[2]), Double.valueOf(s[3]), s[4]));
					}

					return checkIns.iterator();
				}
		 );

		JavaRDD<Vector> vector = javaRDD.mapToPair(checkIn ->
				{
					Set<String> time = Sets.newHashSet();
					time.add(checkIn.getTime());

					Set<String> location = Sets.newHashSet();
					location.add(checkIn.getLocation());

					return new Tuple2<>(checkIn.getUser(), new CheckInObject(1L, time, location));
				}
		).reduceByKey(
				(CheckInObject c1, CheckInObject c2) -> {
					c1.setI(c1.getI() + c2.getI());
					c1.getTime().addAll(c2.getTime());
					c1.getLocation().addAll(c2.getLocation());
					return c1;
				}
		).map(t -> Vectors.dense(t._2.getI().doubleValue(), t._2.getTime().size(), (double)t._2.getLocation().size()));

//		vector.coalesce(1).saveAsTextFile("src/main/resources/data1.txt");

		MultivariateStatisticalSummary summary = Statistics.colStats(vector.rdd());

		System.out.println("Mean :" + summary.mean());
		System.out.println("Variance :" + summary.variance());
		System.out.println("NumNonzeros :" + summary.numNonzeros());

		Matrix correlMatrix = Statistics.corr(vector.rdd(), "pearson");
		System.out.println("correlMatrix:" + correlMatrix.toString());
	}

	@Test
	public void classificationTest(){
//		SQLContext sqlContext = new SQLContext(javaSparkContext);
//
//		 sqlContext.read().textFile("/Downloads/book2-master/2rd_data/ch02/Gowalla_totalCheckins.txt")

		JavaPairRDD<String, Set<String>> javaRDD =  javaSparkContext.textFile("/Downloads/book2-master/2rd_data/ch02/Gowalla_totalCheckins.txt")
				.map(line -> line.split("~"))
				.map(s -> new AppDto(s[0],s[1],s[2],s[3],s[4]))
				.mapToPair(app -> {
					Set<String> setIntro = Arrays.stream(app.getIntroduction().split(" "))
							.map(s -> s.split("/"))
							.filter(ss -> ss[0].length()>1 && (ss[1].equals("v") || ss[1].indexOf("n")>-1))
							.map(ss -> ss[0]).collect(Collectors.toSet());

					return new Tuple2<>(app.getCls(), setIntro);
				});

		javaRDD.map(t -> t._2).zipWithIndex();
	}
}

