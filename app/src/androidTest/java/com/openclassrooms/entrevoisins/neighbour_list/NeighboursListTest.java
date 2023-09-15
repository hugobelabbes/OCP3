
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourPagerAdapter;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.size();
    private static int FIRST_POSITION_ITEM = 0;

    private ListNeighbourActivity mActivity;
    private NeighbourApiService mService;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mService = DI.getNewInstanceApiService();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myNeighboursList_onClickItem_shouldOpenProfileActivityCorrectly() {
        // Given : We open the Profile Activity of the first neighbour of the list
        // When : perform a click on the first item of the list
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(FIRST_POSITION_ITEM, click()));

        // Then : we check if all elements that should be displayed on profile activity are displayed
        onView(withId(R.id.user_avatar))
                .check(matches(isDisplayed()));
        onView(withId(R.id.user_name))
                .check(matches(isDisplayed()));
        onView(withId(R.id.return_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.fav_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.cardView_adress))
                .check(matches(isDisplayed()));
        onView(withId(R.id.cardView_about))
                .check(matches(isDisplayed()));
    }

    @Test
    public void userName_onProfileActivity_isCorrect() {
        List<Neighbour> neighbourList = mService.getNeighbours();
        Neighbour neighbour = neighbourList.get(FIRST_POSITION_ITEM);

        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(FIRST_POSITION_ITEM, click()));

        onView(withId(R.id.user_name))
                .check(matches(withText(neighbour.getName())));
        onView(withId(R.id.adress_label))
                .check(matches(withText(neighbour.getAddress())));
        onView(withId(R.id.phone_label))
                .check(matches(withText(neighbour.getPhoneNumber())));
        onView(withId(R.id.about_label))
                .check(matches(withText(neighbour.getAboutMe())));
    }


    @Test
    public void myFavoritesList_onFavoriteTabItem_showOnlyFavoriteNeighbours() {

        //First add a neighbour as favorite
        onView(withId(R.id.container))
                .perform(swipeRight());
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(FIRST_POSITION_ITEM, click()));
        onView(withId(R.id.fav_button))
                .perform(click());
        pressBack();

        //Then check back is there is 1 favorite neighbour in the favorite tab
        onView(withId(R.id.container))
                .perform(swipeLeft());
        onView(withId(R.id.list_neighbours_favo))
                .check(withItemCount(1));
    }
}