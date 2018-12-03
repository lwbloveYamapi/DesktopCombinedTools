package com.edu;

import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.show.api.ShowApiRequest;

public class Post {
	String url = "http://route.showapi.com/64-19";
	String id = "78438";
	String secert = "613433a8eea743a389409c8ac0c9b149";
	public static String getPostDetails(String nu) {
		String res=new ShowApiRequest("http://route.showapi.com/64-19","78438","613433a8eea743a389409c8ac0c9b149")
				.addTextPara("com","auto")
				.addTextPara("nu",nu)
				.post();
		return res;
	}
	static Postpojo postString2Pojo(String nu) {
		Postpojo postpojo = new Postpojo();
		String poString = getPostDetails(nu);
		JSONObject jsonObject = JSONObject.parseObject(poString);
		jsonObject = jsonObject.getJSONObject("showapi_res_body");
		String name = jsonObject.getString("expTextName");
		nu = jsonObject.getString("mailNo");
		postpojo.setName(name);
		postpojo.setNu(nu);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		List<Address> list = new ArrayList<>();
		if(jsonArray.size()>0){
			for(int i=0;i<jsonArray.size();i++){
				Address address = new Address();
				JSONObject job = jsonArray.getJSONObject(i);
				address.setTime(job.getString("time"));
				address.setContext(job.getString("context"));
				list.add(address);
			}
		}
		postpojo.setList(list);
		return postpojo;
	}
	static String getPostContext(String nu) {
		long start = System.currentTimeMillis();
		Postpojo postpojo =  postString2Pojo(nu);
		String details = "";
		long spend = System.currentTimeMillis()-start;
		details += "本次查询耗时："+spend+"ms\n";
		details += "单号："+postpojo.getNu()+"\n";
		details += "快递承运单位："+postpojo.getName()+"\n";
		for(Address address : postpojo.getList()) {
			details += "时间点："+address.getTime()+"\n";
			details += "动作："+address.getContext()+"\n";
		}
		return details;
	}
		public static void main(String[] args) {
//			System.out.println(postString2Pojo("632671345422"));
			String postpojo =  getPostContext("632671345422");
			System.out.println(postpojo);
		}

	}
