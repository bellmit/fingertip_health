//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//
//import android.app.Activity;
//import android.support.test.espresso.Espresso;
//import android.support.test.espresso.IdlingResource;
//import android.support.test.espresso.ViewInteraction;
//import android.support.test.espresso.contrib.RecyclerViewActions;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
//import android.support.test.runner.lifecycle.Stage;
//import android.test.suitebuilder.annotation.LargeTest;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.LogUtil;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.hamcrest.core.IsInstanceOf;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.Collection;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
//import static android.support.test.espresso.action.ViewActions.replaceText;
//import static android.support.test.espresso.action.ViewActions.scrollTo;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withParent;
//import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class AllActivityTest {
//    public static final int TRANSITION_DELAY=1000;
//
//    @Rule
//    public ActivityTestRule<LoginActivityNew> mActivityTestRule = new ActivityTestRule<>(LoginActivityNew.class);
//
//
//
//    public Activity getActivityInstance(){
//        final Activity[] currentActivity = {null};
//        try {
//            mActivityTestRule.runOnUiThread(new Runnable() {
//                public void run() {
//                    Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
//                    for (Activity act: resumedActivities){
//                        LogUtil.i("Your current activity: " + act.getClass().getName());
//                        currentActivity[0] = act;
//                        break;
//                    }
//                }
//            });
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//
//        return currentActivity[0];
//    }
//
//    @Test
//    public void allActivityTest() {
//        Activity currentActivity = getActivityInstance();
//        IdlingResource idlingResource = new ProgressDialogIdlingResource(currentActivity);
//        Espresso.registerIdlingResources(idlingResource);
//
//        ViewInteraction appCompatEditText = onView(
//                withId(R.id.acet_username));
//        appCompatEditText.perform(scrollTo(), click());
//
//        ViewInteraction appCompatEditText2 = onView(
//                withId(R.id.acet_username));
//        appCompatEditText2.perform(scrollTo(), replaceText("342123193810151809"), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText3 = onView(
//                allOf(withId(R.id.acet_username), withText("342123193810151809")));
//        appCompatEditText3.perform(pressImeActionButton());
//
//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.bt_password_eye), isDisplayed()));
//        appCompatButton.perform(click());
//
//        ViewInteraction appCompatEditText4 = onView(
//                withId(R.id.acet_password));
//        appCompatEditText4.perform(scrollTo(), click());
//
//        ViewInteraction appCompatEditText5 = onView(
//                withId(R.id.acet_password));
//        appCompatEditText5.perform(scrollTo(), replaceText("a123456"), closeSoftKeyboard());
//
//        ViewInteraction appCompatButton2 = onView(
//                allOf(withId(R.id.bt_login), withText("登录")));
//        appCompatButton2.perform(scrollTo(), click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView = onView(
//                allOf(withId(R.id.recyclerview),
//                        withParent(allOf(withId(R.id.lay_content_policy),
//                                withParent(withId(R.id.lay_policy)))),
//                        isDisplayed()));
//        recyclerView.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout.perform(closeSoftKeyboard(), click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatTextView = onView(
//                allOf(withId(R.id.tv_policy_more), withText("更多>"),
//                        withParent(withId(R.id.lay_policy))));
//        appCompatTextView.perform(scrollTo(), click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView2 = onView(
//                allOf(withId(R.id.recyclerview),
//                        withParent(allOf(withId(R.id.lay_content),
//                                withParent(withId(R.id.framelayout)))),
//                        isDisplayed()));
//        recyclerView2.perform(actionOnItemAtPosition(0, click()));
//
//        ViewInteraction linearLayout2 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout2.perform(closeSoftKeyboard(), click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout3 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout3.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatTextView2 = onView(
//                allOf(withId(R.id.tv_remind_family), withText("显示家庭成员提醒>")));
//        appCompatTextView2.perform(scrollTo(), click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView3 = onView(
//                allOf(withId(R.id.recyclerview),
//                        withParent(allOf(withId(R.id.lay_content),
//                                withParent(withId(R.id.framelayout)))),
//                        isDisplayed()));
//        recyclerView3.perform(actionOnItemAtPosition(1, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView4 = onView(
//                allOf(withId(R.id.recyclerview),
//                        withParent(withId(R.id.lay_content_remind)),
//                        isDisplayed()));
//        recyclerView4.perform(actionOnItemAtPosition(2, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout4 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout4.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout5 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(allOf(withId(R.id.toolbar),
//                                withParent(withId(R.id.topPanel)))),
//                        isDisplayed()));
//        linearLayout5.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout6 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout6.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView5 = onView(
//                allOf(withId(R.id.recyclerview),
//                        withParent(withId(R.id.lay_content_remind)),
//                        isDisplayed()));
//        recyclerView5.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatTextView3 = onView(
//                allOf(withId(R.id.tv_load_failure_hint), withText("暂无近7天需要执行项目,点我刷新"),
//                        withParent(allOf(withId(R.id.lay_execution_projects_load_failure),
//                                withParent(withId(R.id.framelayout)))),
//                        isDisplayed()));
//        appCompatTextView3.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout7 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout7.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction roundTextView = onView(
////                allOf(withId(R.id.rtv_msg_tip), isDisplayed()));
////        roundTextView.perform(click());
//        ViewInteraction secondTabView = onView(
//                allOf(withTagValue(new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)1){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                }), isDisplayed())
//        );
//        secondTabView.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView6 = onView(
//                allOf(withId(R.id.recyclerview),
//                        withParent(allOf(withId(R.id.srl),
//                                withParent(withId(R.id.framelayout)))),
//                        isDisplayed()));
//        recyclerView6.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatImageView = onView(
//                allOf(withId(R.id.iv_phone),
//                        withParent(allOf(withId(R.id.ll_top),
//                                withParent(withId(R.id.ll_first))))));
//        appCompatImageView.perform(scrollTo(), click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction mDButton = onView(
//                allOf(withId(R.id.md_buttonDefaultNegative), withText("取消"), isDisplayed()));
//        mDButton.perform(click());
//
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction recyclerView7 = onView(
////                allOf(withId(R.id.id_recyclerview_horizontal), isDisplayed()));
////        recyclerView7.perform(actionOnItemAtPosition(1, click()));
////
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction linearLayout8 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar)),
////                        isDisplayed()));
////        linearLayout8.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction relativeLayout = onView(
//                allOf(withId(R.id.rl_execu), isDisplayed()));
//        relativeLayout.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction linearLayout9 = onView(
////                allOf(childAtPosition(
////                        withId(R.id.lv_execued),
////                        2),
////                        isDisplayed()));
////        linearLayout9.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction linearLayout10 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar))));
////        linearLayout10.perform(scrollTo(), click());
////
////        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout11 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout11.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction relativeLayout2 = onView(
//                allOf(withId(R.id.rl_accessfile), isDisplayed()));
//        relativeLayout2.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout12 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout12.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction relativeLayout3 = onView(
//                allOf(withId(R.id.rl_updateinfo), isDisplayed()));
//        relativeLayout3.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatImageView2 = onView(
//                allOf(withId(R.id.iv_update_head),
//                        withParent(allOf(withId(R.id.ll_idcard),
//                                withParent(withId(R.id.ll_data)))),
//                        isDisplayed()));
//        appCompatImageView2.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatImageButton = onView(
//                allOf(withContentDescription("Navigate up"),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        appCompatImageButton.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout13 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout13.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout14 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout14.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction roundTextView2 = onView(
////                allOf(withId(R.id.rtv_msg_tip), isDisplayed()));
////        roundTextView2.perform(click());
//        ViewInteraction thirdTabView = onView(allOf(withTagValue(
//                new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)2){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                }
//        )));
//        thirdTabView.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView8 = onView(
//                allOf(withId(R.id.recyclerview),
//                        withParent(withId(R.id.lay_content_remind)),
//                        isDisplayed()));
//        recyclerView8.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout15 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(allOf(withId(R.id.toolbar),
//                                withParent(withId(R.id.topbar)))),
//                        isDisplayed()));
//        linearLayout15.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction roundTextView3 = onView(
////                allOf(withId(R.id.rtv_msg_tip), isDisplayed()));
////        roundTextView3.perform(click());
//        ViewInteraction fourthTabView = onView(allOf(withTagValue(
//                new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)3){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                }
//        )));
//        fourthTabView.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView9 = onView(
//                allOf(
////                        withId(R.id.recyclerview),
//                        withTagValue(new Matcher<Object>() {
//                            @Override
//                            public boolean matches(Object item) {
//                                if (item==(Integer)10){
//                                    return true;
//                                }
//                                return false;
//                            }
//
//                            @Override
//                            public void describeMismatch(Object item, Description mismatchDescription) {
//
//                            }
//
//                            @Override
//                            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                            }
//
//                            @Override
//                            public void describeTo(Description description) {
//
//                            }
//                        })));
//        recyclerView9.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatTextView4 = onView(
//                withId(R.id.addseverpake_btn));
//        appCompatTextView4.perform(scrollTo(), click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView10 = onView(
//                allOf(withId(R.id.recyclerview), isDisplayed()));
//        recyclerView10.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout16 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout16.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView11 = onView(
//                allOf(withId(R.id.recyclerview), isDisplayed()));
//        recyclerView11.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout17 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout17.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView12 = onView(
//                allOf(withId(R.id.recyclerview), isDisplayed()));
//        recyclerView12.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout18 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout18.perform(closeSoftKeyboard(), click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView13 = onView(
//                allOf(withId(R.id.recyclerview), isDisplayed()));
//        recyclerView13.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout19 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout19.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatTextView5 = onView(
//                allOf(withText("其他"), isDisplayed()));
//        appCompatTextView5.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout20 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(allOf(withId(R.id.toolbar),
//                                withParent(withId(R.id.layone)))),
//                        isDisplayed()));
//        linearLayout20.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout21 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(allOf(withId(R.id.toolbar),
//                                withParent(withId(R.id.topPanel)))),
//                        isDisplayed()));
//        linearLayout21.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView14 = onView(
//                allOf(withTagValue(new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)10){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                })));
//        recyclerView14.perform(actionOnItemAtPosition(1, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction fourthTabView2 = onView(allOf(withTagValue(
//                new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)3){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                }
//        )));
//        fourthTabView2.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView15 = onView(
//                allOf(withTagValue(new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)10){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                })));
//        recyclerView15.perform(actionOnItemAtPosition(2, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout22 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(allOf(withId(R.id.toolbar),
//                                withParent(withId(R.id.topPanel)))),
//                        isDisplayed()));
//        linearLayout22.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction recyclerView16 = onView(
////                allOf(withTagValue(new Matcher<Object>() {
////                    @Override
////                    public boolean matches(Object item) {
////                        if (item==(Integer)10){
////                            return true;
////                        }
////                        return false;
////                    }
////
////                    @Override
////                    public void describeMismatch(Object item, Description mismatchDescription) {
////
////                    }
////
////                    @Override
////                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
////
////                    }
////
////                    @Override
////                    public void describeTo(Description description) {
////
////                    }
////                })));
////        recyclerView16.perform(actionOnItemAtPosition(3, click()));
//
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction recyclerView17 = onView(
////                allOf(withId(R.id.recyclerview),
////                        withParent(allOf(withId(R.id.lay_content),
////                                withParent(withId(R.id.framelayout)))),
////                        isDisplayed()));
////        recyclerView17.perform(RecyclerViewActions.scrollToPosition(0), actionOnItemAtPosition(0, click()));
////
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction linearLayout23 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar)),
////                        isDisplayed()));
////        linearLayout23.perform(click());
//
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction linearLayout24 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar)),
////                        isDisplayed()));
////        linearLayout24.perform(click());
//
////        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction recyclerView18 = onView(
////                allOf(withTagValue(new Matcher<Object>() {
////                    @Override
////                    public boolean matches(Object item) {
////                        if (item==(Integer)10){
////                            return true;
////                        }
////                        return false;
////                    }
////
////                    @Override
////                    public void describeMismatch(Object item, Description mismatchDescription) {
////
////                    }
////
////                    @Override
////                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
////
////                    }
////
////                    @Override
////                    public void describeTo(Description description) {
////
////                    }
////                })));
////        recyclerView18.perform(actionOnItemAtPosition(4, click()));
////
////        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction recyclerView19 = onView(
////                allOf(withId(R.id.recyclerview), isDisplayed()));
////        recyclerView19.perform(actionOnItemAtPosition(0, click()));
////
////        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction linearLayout25 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar)),
////                        isDisplayed()));
////        linearLayout25.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction linearLayout26 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar)),
////                        isDisplayed()));
////        linearLayout26.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView20 = onView(
//                allOf(withTagValue(new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)11){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                })));
//        recyclerView20.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView21 = onView(
//                allOf(withId(R.id.recyclerview),
//                        withParent(withId(R.id.lay_content_remind)),
//                        isDisplayed()));
////        recyclerView21.perform(actionOnItemAtPosition(10, click()));
//        recyclerView21.perform(RecyclerViewActions.scrollToPosition(10), actionOnItemAtPosition(10, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatTextView6 = onView(
//                allOf(withId(R.id.tv_load_failure_hint), withText("暂无近7天需要执行项目,点我刷新"),
//                        withParent(allOf(withId(R.id.lay_execution_projects_load_failure),
//                                withParent(withId(R.id.framelayout)))),
//                        isDisplayed()));
//        appCompatTextView6.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout27 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout27.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction linearLayout28 = onView(
////                allOf(withId(R.id.ll_dial), isDisplayed()));
////        linearLayout28.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction linearLayout29 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(allOf(withId(R.id.toolbar)
//////                                ,
//////                                withParent(withId(R.id.topPanel))
////                        )),
////                        isDisplayed()));
////        linearLayout29.perform(click());
//
//        ViewInteraction linearLayoutTemp = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayoutTemp.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
////        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView22 = onView(
//                allOf(withTagValue(new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)11){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                })));
//        recyclerView22.perform(actionOnItemAtPosition(1, click()));
//
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction appCompatButton3 = onView(
////                allOf(withId(R.id.btn_execu), withText("待评价"), isDisplayed()));
////        appCompatButton3.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction linearLayout30 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar)),
////                        isDisplayed()));
////        linearLayout30.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction appCompatButton4 = onView(
////                allOf(withId(R.id.btn_execu), withText("已评价"), isDisplayed()));
////        appCompatButton4.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction linearLayout31 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar)),
////                        isDisplayed()));
////        linearLayout31.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout32 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout32.perform(click());
//
//        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction recyclerView23 = onView(
////                allOf(withTagValue(new Matcher<Object>() {
////                    @Override
////                    public boolean matches(Object item) {
////                        if (item==(Integer)11){
////                            return true;
////                        }
////                        return false;
////                    }
////
////                    @Override
////                    public void describeMismatch(Object item, Description mismatchDescription) {
////
////                    }
////
////                    @Override
////                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
////
////                    }
////
////                    @Override
////                    public void describeTo(Description description) {
////
////                    }
////                })));
////        recyclerView23.perform(actionOnItemAtPosition(2, click()));
//
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction linearLayout33 = onView(
////                allOf(childAtPosition(
////                        withId(R.id.lv_execued),
////                        3),
////                        isDisplayed()));
////        linearLayout33.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction linearLayout34 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar))));
////        linearLayout34.perform(scrollTo(), click());
//
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction linearLayout35 = onView(
////                allOf(withId(R.id.ll_back),
////                        withParent(withId(R.id.toolbar)),
////                        isDisplayed()));
////        linearLayout35.perform(click());
//
////        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction recyclerView24 = onView(
////                allOf(withTagValue(new Matcher<Object>() {
////                    @Override
////                    public boolean matches(Object item) {
////                        if (item==(Integer)11){
////                            return true;
////                        }
////                        return false;
////                    }
////
////                    @Override
////                    public void describeMismatch(Object item, Description mismatchDescription) {
////
////                    }
////
////                    @Override
////                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
////
////                    }
////
////                    @Override
////                    public void describeTo(Description description) {
////
////                    }
////                })));
////        recyclerView24.perform(actionOnItemAtPosition(3, click()));
////
////        Util.sleep(TRANSITION_DELAY);
////
////        ViewInteraction mDButton2 = onView(
////                allOf(withId(R.id.md_buttonDefaultNegative), withText("取消"), isDisplayed()));
////        mDButton2.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView25 = onView(
//                allOf(withTagValue(new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)11){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                })));
//        recyclerView25.perform(actionOnItemAtPosition(4, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction roundTextView5 = onView(
////                allOf(withId(R.id.rtv_msg_tip), isDisplayed()));
////        roundTextView5.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
//        ViewInteraction fourthTabView3 = onView(allOf(withTagValue(
//                new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)3){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                }
//        )));
//        fourthTabView3.perform(click());
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction recyclerView26 = onView(
//                allOf(withTagValue(new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)11){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                })));
//        recyclerView26.perform(actionOnItemAtPosition(5, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
////        ViewInteraction roundTextView6 = onView(
////                allOf(withId(R.id.rtv_msg_tip), isDisplayed()));
////        roundTextView6.perform(click());
////
////        Util.sleep(TRANSITION_DELAY);
//        fourthTabView3.perform(click());
//        Util.sleep(TRANSITION_DELAY);
//
//
//        ViewInteraction recyclerView27 = onView(
//                allOf(withTagValue(new Matcher<Object>() {
//                    @Override
//                    public boolean matches(Object item) {
//                        if (item==(Integer)12){
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public void describeMismatch(Object item, Description mismatchDescription) {
//
//                    }
//
//                    @Override
//                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
//
//                    }
//
//                    @Override
//                    public void describeTo(Description description) {
//
//                    }
//                })));
//        recyclerView27.perform(actionOnItemAtPosition(0, click()));
//
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction linearLayout36 = onView(
//                withId(R.id.ll_check_update));
//        linearLayout36.perform(scrollTo(), click());
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Util.sleep(TRANSITION_DELAY);
//
//        ViewInteraction appCompatButton5 = onView(
//                allOf(withId(R.id.bt_exit_login), withText("退出登录")));
//        appCompatButton5.perform(scrollTo(), click());
//
//        ViewInteraction mDButton3 = onView(
//                allOf(withId(R.id.md_buttonDefaultNegative), withText("取消"), isDisplayed()));
//        mDButton3.perform(click());
//
//        ViewInteraction linearLayout37 = onView(
//                allOf(withId(R.id.ll_back),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        linearLayout37.perform(click());
//
//        ViewInteraction textView = onView(
//                allOf(withId(R.id.tv_tab_title), withText("全部"),
//                        childAtPosition(
//                                allOf(withId(R.id.ll_tap),
//                                        childAtPosition(
//                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        textView.check(matches(withText("全部")));
//
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}
