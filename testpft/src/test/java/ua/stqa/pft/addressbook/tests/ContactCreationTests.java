package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(
                new ContactData("test_name", "last_name","test1"),true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnHomePage();
    }

}
