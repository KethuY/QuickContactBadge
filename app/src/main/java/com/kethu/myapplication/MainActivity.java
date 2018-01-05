package com.kethu.myapplication;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    JSONObject mJsonObject;
    private String mJSONStr;
    TextView Email,Phone;
    QuickContactBadge EmailPic,PhonePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (TextView)findViewById(R.id.textView1);
        Phone = (TextView)findViewById(R.id.textView2);
        EmailPic = (QuickContactBadge)findViewById(R.id.quickContactBadge1);
        PhonePic = (QuickContactBadge)findViewById(R.id.quickContactBadge2);

        //Assign the contact badge to Email Pick badge.

        EmailPic.assignContactFromEmail("android@examples.com", true);
        EmailPic.setMode(ContactsContract.QuickContact.MODE_MEDIUM);

        //Assign the contact badge to phone pick badge.

        PhonePic.assignContactFromPhone("+911234567890", true);
        PhonePic.setMode(ContactsContract.QuickContact.MODE_MEDIUM);

    }

    class MyAsynTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                mJSONStr = sendGET();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                mJsonObject = new JSONObject(mJSONStr);

                Weather weather = new Weather();//Overall object
                weather.setCnt(mJsonObject.getInt("cnt"));
                weather.setCod(mJsonObject.getString("cod"));
                weather.setMessage(mJsonObject.getDouble("message"));
                JSONObject cityJson = mJsonObject.getJSONObject("city");

                City city = new City();
                city.setCountry(cityJson.getString("country"));
                city.setId(cityJson.getInt("id"));
                city.setPopulation(cityJson.getInt("population"));

                JSONObject coordJSon = cityJson.getJSONObject("coord");

                Coord coord = new Coord();
                coord.setLat(coordJSon.getDouble("lat"));
                coord.setLon(coordJSon.getDouble("lon"));
                city.setCoord(coord);

                weather.setCity(city);

                List<ListItem> listItems = new ArrayList<>();
                JSONArray jsonArray = mJsonObject.getJSONArray("list");

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ListItem listItem = new ListItem();
                    listItem.setClouds(jsonObject.getInt("clouds"));
                    listItem.setDeg(jsonObject.getInt("deg"));
                    listItem.setSpeed(jsonObject.getDouble("speed"));
                    listItem.setPressure(jsonObject.getDouble("pressure"));
                    listItem.setHumidity(jsonObject.getDouble("humidity"));

                    JSONObject tempJsonObj = jsonObject.getJSONObject("temp");

                    Temp temp = new Temp();
                    temp.setDay(tempJsonObj.getDouble("day"));
                    temp.setMax(tempJsonObj.getDouble("max"));
                    temp.setMin(tempJsonObj.getDouble("min"));
                    temp.setNight(tempJsonObj.getDouble("night"));
                    temp.setEve(tempJsonObj.getDouble("eve"));
                    temp.setMorn(tempJsonObj.getDouble("morn"));
                    listItem.setTemp(temp);

                    List<WeatherItem> weatherItems = new ArrayList<>();
                    JSONArray weJson = jsonObject.getJSONArray("weather");

                    for (int j = 0; j < weJson.length(); j++) {
                        WeatherItem weatherItem = new WeatherItem();

                        JSONObject jsonObject1 = weJson.getJSONObject(j);
                        weatherItem.setId(jsonObject1.getInt("id"));
                        weatherItem.setMain(jsonObject1.getString("main"));
                        weatherItem.setDescription(jsonObject1.getString("description"));
                        weatherItem.setIcon(jsonObject1.getString("icon"));
                        weatherItems.add(weatherItem);
                    }

                    listItem.setWeather(weatherItems);
                    listItems.add(listItem);
                }
                weather.setList(listItems);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private String sendGET() throws IOException {
        URL obj = new URL("");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Weather Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();

        } else {
            System.out.println("GET request not worked");
        }

        return null;
    }


}
