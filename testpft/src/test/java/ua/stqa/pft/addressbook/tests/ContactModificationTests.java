package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(
                new ContactData("test_name 2", "last_name 3",null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnHomePage();
    }
}
