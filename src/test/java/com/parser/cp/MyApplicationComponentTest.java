package com.parser.cp;

import com.parser.cp.model.payload.Task;
import com.parser.cp.util.FileUtility;
import com.parser.cp.util.JacksonUtility;
import org.junit.Before;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

public class MyApplicationComponentTest {
    private static final Logger LOGGER = Logger.getLogger(MyApplicationComponentTest.class.getName());
    private static final String POST_URL = "http://localhost:4243/";
    private static final String HACKER_RANK = "sampleInput.json";
    private String payLoad;

    @Before
    public void initialize() throws Exception {
        String hackerRankSample = FileUtility.readFromResourcesDirectory(HACKER_RANK, getClass().getClassLoader());
        Optional<Task> optionalTask = JacksonUtility.jsonPtrExpr(hackerRankSample, "/hackerRank/single", Task.class);
        if (optionalTask.isPresent()) {
            payLoad = JacksonUtility.deSerialize(optionalTask.get(), false);
        }
    }

    @Test
    public void happyPath() {
        /*try {
            ApplicationComponent applicationComponent = new MyApplicationComponent();
            applicationComponent.initComponent();
            String hackerRankSample = FileUtility.readFromResourcesDirectory(HACKER_RANK, getClass().getClassLoader());
            Optional<Task> optionalTask = JacksonUtility.jsonPtrExpr(hackerRankSample, "/hackerRank/single", Task.class);
            if (optionalTask.isPresent()) {
                payLoad = JacksonUtility.writeValueAsString(optionalTask.get());
                LOGGER.info(payLoad);
            } else {
                Assert.fail("Could not read from resources");
            }


        } catch (Exception e) {
            LOGGER.severe("Error occurred during testing " + e.getLocalizedMessage());
        }*/
    }


    public void myPost() throws Exception {
        URL url = new URL(POST_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Length", Integer.toString(payLoad.length()));
        httpURLConnection.setUseCaches(false);
        OutputStream outputStream = httpURLConnection.getOutputStream();

        byte[] postData = payLoad.getBytes(StandardCharsets.UTF_8);
        try (DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream())) {
            wr.write(postData);
        }

        outputStream.flush();
        outputStream.close();
        httpURLConnection.disconnect();
    }

    /**
     * <a href="https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/">Reference for method implementation</a>
     *
     * @throws Exception Spills any exception
     */
    public void sendPost() throws Exception {
        final String USER_AGENT = "Mozilla/5.0";
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = payLoad;
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + POST_URL);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
    }

}
