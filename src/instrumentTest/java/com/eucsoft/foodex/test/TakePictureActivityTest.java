package com.eucsoft.foodex.test;

import android.test.ActivityInstrumentationTestCase2;

import com.eucsoft.foodex.R;
import com.eucsoft.foodex.TakePictureActivity;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class TakePictureActivityTest extends ActivityInstrumentationTestCase2<TakePictureActivity>

{
    //^Activity to test
    private TakePictureActivity takePictureActivity;

    // Be careful about letting the IDE create the constructor.  As of this writing,
    // it creates a constructor that's compiles cleanly but doesn't run any tests
    public TakePictureActivityTest() {
        super(TakePictureActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //if (takePictureActivity!=null)
        //   //takePictureActivity.finish();
        //Sleep is necessary because Camera Service is not always freed in time
        Thread.sleep(500);
        takePictureActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // Methods whose names are prefixed with test will automatically be run
    public void testTakePictureOnStart() {
        onView(withId(R.id.cameraPreview)).check(matches(isDisplayed()));
        onView(withId(R.id.select_photo_button)).check(matches(isDisplayed()));
        onView(withId(R.id.take_picture_button)).check(matches(isDisplayed()));
        onView(withId(R.id.back_button)).check(matches(isDisplayed()));
        onView(withId(R.id.upload_photo_button)).check(matches(not(isDisplayed())));
    }

    public void testTakePictureOnReStart() {
        assertNotNull(takePictureActivity);
        //getInstrumentation().callActivityOnRestart(takePictureActivity);
        //getInstrumentation().waitForIdleSync();

        takePictureActivity.finish();
        setActivity(null);

        //Sleep is necessary because Camera Service is not always freed in time and Activity not starts properly
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        takePictureActivity = getActivity();
        onView(withId(R.id.cameraPreview)).check(matches(isDisplayed()));
        onView(withId(R.id.select_photo_button)).check(matches(isDisplayed()));
        onView(withId(R.id.take_picture_button)).check(matches(isDisplayed()));
        onView(withId(R.id.back_button)).check(matches(isDisplayed()));
        onView(withId(R.id.upload_photo_button)).check(matches(not(isDisplayed())));
    }

    //TODO: Findout how to work with external Activities (looks like impossible)
    // Methods whose names are prefixed with test will automatically be run
        /*public void testTakePictureAfterPhotoSelected(){
            onView(withId(R.id.select_photo_button)).perform(click());
            onView(withId(R.id.cameraPreview)).check(matches(isDisplayed()));
            onView(withId(R.id.select_photo_button)).check(matches(isDisplayed()));
            onView(withId(R.id.take_picture_button)).check(matches(isDisplayed()));
            onView(withId(R.id.back_button)).check(matches(isDisplayed()));
            onView(withId(R.id.upload_photo_button)).check(doesNotExist());
        }*/

    // Methods whose names are prefixed with test will automatically be run
    public void testTakePictureAfterPictureTaken() {
        onView(withId(R.id.take_picture_button)).perform(click());
        onView(withId(R.id.cameraPreview)).check(matches(isDisplayed()));
        onView(withId(R.id.select_photo_button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.take_picture_button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.back_button)).check(matches(isDisplayed()));
        onView(withId(R.id.upload_photo_button)).check(matches(isDisplayed()));
    }
}
