package com.github.kubesys.devfrk.spring.rda.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.kubesys.devfrk.spring.rda.models.Question;

import io.github.kubesys.devfrk.spring.cores.AbstractHttpHandler;
import io.github.kubesys.devfrk.spring.data.RelationalDataAccess;
import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Jennifer Reif
 * @author Michael J. Simons
 */
@ServiceDefinition
public class RdaService extends AbstractHttpHandler  {

	@Autowired
	private RelationalDataAccess rda;

	public void record(String path, int num) throws Exception {
		
		Question q1 = new Question();
		q1.setAnswers("【答案】B\r\n"
				+ "\r\n"
				+ "【解析】A 项, 铁是单质, 不溶于水, 铁本身能导电, 但不是电解质, 也不是非电解质, 故 A 错误; B 项, \\(\\mathrm{SO}_{2}\\) 的水溶液能导电, 但 \\(\\mathrm{SO}_{2}\\) 本身是非电解质, 故 B 正确; \\(\\mathrm{C}\\) 项, 酒精的水溶液不能导电, 酒精本身是非电解 质, 故 C 错误; \\(\\mathrm{D}\\) 项，硫酸钡晶体不溶于水, 水溶液不导电, 但硫酸钡是电解质, 故 D 错误; 故选 B。\r\n"
				+ "");
		q1.setContent("下列物质中, 其水溶液能导电, 但本身属于非电解质的是\r\n"
				+ "A. 金属铁\r\n"
				+ "B. \\(\\mathrm{SO}_{2}\\)\r\n"
				+ "C. 酒精\r\n"
				+ "D. 硫酸钡晶体\r\n"
				+ "");
		
		Question q2 = new Question();
		q1.setAnswers("【答案】D\r\n"
				+ "\r\n"
				+ "【解析】A 项, 无水乙醇是非电解质, 无论什么条件下都不会导电, 故 A 不符合题意; \\(\\mathrm{B}\\) 项, 氯化氢是电 解质, 只有在水溶液中才能电离出自由移动的离子, 才会导电, 但气体中无离子, 不导电, 故 \\(\\mathrm{B}\\) 不符合题意; \\(\\mathrm{C}\\) 项, 氢氧化钠固体是电解质, 在水溶液或熔融状态下都能电离出自由移动的离子, 会导电, 但固体中离子不 能自由移动, 不导电, 故 C 不符合题意; D 项, 熔融氯化钠是电解质, 在熔融状态下能电离出自由移动的离子, 会导电, 故 D 符合题意; 故选 D。\r\n"
				+ "");
		q1.setContent("下列物质中能导电的是 ( )\r\n"
				+ "A. 无水乙醇\r\n"
				+ "B. 氯化氢气体\r\n"
				+ "C. 氢氧化钠固体\r\n"
				+ "D. 熔融氯化钠\r\n"
				+ "");
		
	    if (num < 1 || num > 2) {
			throw new Exception("测试版本只支持一次录入2道题");
		}
		
		if (num == 1) {
			rda.create(q1);
		} else {
			rda.create(q1);
			rda.create(q2);
		}
	}
}
