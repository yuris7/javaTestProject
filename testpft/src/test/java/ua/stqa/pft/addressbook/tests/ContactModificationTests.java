package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.ContactData;
import ua.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Test1")
                .withLastName("Test2");
        app.goTo().contactPage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
    }
}
