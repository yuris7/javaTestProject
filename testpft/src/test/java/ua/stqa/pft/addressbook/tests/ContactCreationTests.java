package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.contact().getContactList();
        File photo = new File("src/test/resources/cow.png");
        ContactData contact = new ContactData()
                      .withFirstName("yurii")
                      .withLastName("Voina")
                      .withNickName("")
                      .withTitle("")
                      .withCompany("")
                      .withAddress("Kyiv, Ukraine")
                      .withHomePhone("+123456789")
                      .withMobilePhone("+12345678")
                      .withWorkPhone("+1234567")
                      .withFaxPhone("")
                      .withEmail1("email1@email.com")
                      .withEmail2("email2@email.com")
                      .withEmail3("email3@email.com")
                      .withPhoto(photo);
        app.contact().homePage();
        app.goTo().contactPage();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
    }
}
