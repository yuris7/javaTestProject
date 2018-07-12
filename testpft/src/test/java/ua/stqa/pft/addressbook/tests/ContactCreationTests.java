package ua.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test(enabled = false)
    public void testContactCreation() {
        List<ContactData> before = app.contact().getContactList();
        app.contact().initContactCreation();
        app.contact().fillContactForm(
                new ContactData().withFirstName("test_name").withLastName("last_name").withGroup("test1"), true);
        app.contact().submitContactCreation();
        app.contact().returnHomePage();
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
