package com.morandev.repoconpostgresql;

import com.morandev.repoconpostgresql.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.Assert.fail;

@SpringBootTest
class RepoconpostgresqlApplicationTests {

	/**
	 * escenario: crear un vuelo
	 */
	@Test
	public void postCreateFlight() throws JSONException {
		//Given
		String urlAPI = "http://localhost:8080/flights/";

		JSONObject inputJson = new JSONObject();

		try {
			//When
			inputJson.put("route", "Buenos Aires - Argentina");
			inputJson.put("dateandtime", "2022-01-01 08:00");
			String result = new HttpUtils().sendPost( urlAPI, inputJson );

			//Then
			Assertions.assertTrue(result.contains("Buenos Aires - Argentina"));

			//But
		} catch (IOException e){
			e.printStackTrace();
			fail("Fail: IOException when creating booking");
		}
	}

}
