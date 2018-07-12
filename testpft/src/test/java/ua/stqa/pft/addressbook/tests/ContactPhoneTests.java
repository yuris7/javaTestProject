package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

//  private String mergeEmails(ContactData contact) {
//    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
//            .stream().filter((s) -> ! s.equals(""))
//            .map(ContactPhoneTests::cleaned)
//            .collect(Collectors.joining("\n"));
//  }

//  private String mergeAddress(ContactData contact) {
//    return Arrays.asList(contact.getAddress())
//            .stream().filter((s) -> ! s.equals(""))
//            .collect(Collectors.joining("\n"));
//     }
//
  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
