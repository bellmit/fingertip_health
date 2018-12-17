package com.jqsoft.fingertip_health;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void testHttpUrl(){
        String baseHttpUrl = "http://192.168.88.123:8080/sri/fdss";

        testHttpUrlRegex(baseHttpUrl);

        String baseHttpUrl2 = "http://192.168.88.123:8080/";
        testHttpUrlRegex(baseHttpUrl2);

    }

    private void testHttpUrlRegex(String baseHttpUrl) {
        String regex = "http://(.*?)/";
//            String regex = "http://(.*)/sri/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(baseHttpUrl);
        String sBaseHttpUrl ="";

        while (matcher.find()) {
            // System.out.println(matcher.group(1));
            sBaseHttpUrl=  matcher.group(1);
        }
        println(sBaseHttpUrl);
    }

    @Test
    public void testCopyList(){
        List<String> titleList = new ArrayList<>();
        titleList.add("first");
        titleList.add("second");
        titleList.add("third");
        printList(titleList);

        List<String> result = Util.getProcessedStatisticsChartTitleList(titleList);
        printList(result);
    }

    @Test
    public void testReverseList() {
        Float[] array = new Float[]{1.2f, 1113f, 25f, 1f, 100f};
        List<Float> list = Arrays.asList(array);
        printList(list);
        Util.processStatisticsChartList(list);
        printList(list);
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testLongitudeLatitude(){
        String first = "117", second="123456", third="12345678", fourth="1234567890123";
//        println(Util.getCommaSeparatedIntString(first));
//        println(Util.getCommaSeparatedIntString(second));
//        println(Util.getCommaSeparatedIntString(third));
//        println(Util.getCommaSeparatedIntString(fourth));

        println(Util.getCommaSeparatedForcedIntString(first));
        println(Util.getCommaSeparatedForcedIntString(second));
        println(Util.getCommaSeparatedForcedIntString(third));
        println(Util.getCommaSeparatedForcedIntString(fourth));

        String s0="12345", s1="123456.23", s2="123456789012.3", s3="12.345";
        println(Util.getNonscientificNumberStringFromString(s0));
        println(Util.getNonscientificNumberStringFromString(s1));
        println(Util.getNonscientificNumberStringFromString(s2));
        println(Util.getNonscientificNumberStringFromString(s3));

//        String s1 = "117.31";
//        String sResult = Util.getCanonicalLongitudeOrLatitude(s1);
//        println(sResult);
//        assertEquals("117.310000", sResult);
//        s1="117.32123588";
//        sResult = Util.getCanonicalLongitudeOrLatitude(s1);
//        println(sResult);
//        assertEquals("117.321236", sResult);
//        s1="117.3899212";
//        sResult = Util.getCanonicalLongitudeOrLatitude(s1);
//        println(sResult);
//        assertEquals("117.389921", sResult);
//        s1="117";
//        sResult = Util.getCanonicalLongitudeOrLatitude(s1);
//        println(sResult);
//        assertEquals("117.000000", sResult);
    }

    public <T> void printList(List<T> list){
        if (!ListUtils.isEmpty(list)){
            for (int i = 0; i < list.size(); ++i){
                print(list.get(i)+ Constants.SPACE_STRING);
            }
        }
    }

    public void print(String s){
        System.out.print(s);
    }

    public void println(String s){
        System.out.println(s);
    }
}