//package ua.stqa.pft.addressbook.tests;
//
//import org.testng.annotations.Test;
//import ua.stqa.pft.addressbook.model.ContactData;
//
//public class ContactModificationTests extends TestBase{
//
//    @Test(enabled = false)
//    public void testContactModification() {
//        app.goTo().gotoHomePage();
//        app.contact().initContactModification();
//        app.contact().fillContactForm(
//                new ContactData().withFirstName("test_name 2").withLastName("last_name 3"), false);
//        app.contact().submitContactModification();
//        app.contact().returnHomePage();
//    }
//}
