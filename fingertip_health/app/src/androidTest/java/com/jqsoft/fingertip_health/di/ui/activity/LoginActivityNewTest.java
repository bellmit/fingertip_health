package com.jqsoft.fingertip_health.di.ui.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.jqsoft.fingertip_health.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityNewTest {

    @Rule
    public ActivityTestRule<LoginActivityNew> mActivityTestRule = new ActivityTestRule<>(LoginActivityNew.class);

    @Test
    public void loginActivityNewTest() {
        ViewInteraction appCompatEditText = onView(
                withId(R.id.acet_username));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.acet_username));
        appCompatEditText2.perform(scrollTo(), replaceText("342123193810151809"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.acet_username), withText("342123193810151809")));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bt_password_eye), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.acet_password));
        appCompatEditText4.perform(scrollTo(), click());

        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.acet_password));
        appCompatEditText5.perform(scrollTo(), replaceText("a123456"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.bt_login), withText("登录")));
        appCompatButton2.perform(scrollTo(), click());

    }

}
