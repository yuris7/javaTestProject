package ua.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.stqa.pft.addressbook.model.ContactData;
import ua.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.name;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void editContactForm(String firstname, String lastname) {
        type(By.name("firstname"), firstname);
        type(By.name("lastname"), lastname);

    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFaxPhone());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                new Select(wd.findElement(name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
                //  new Select(wd.findElement(name("new_group"))).selectByValue(contactData.getGroups().iterator().next().getName());
                //извлекаем группу и берем ее имя
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {

        click(By.name("submit"));
    }

    public void returnHomePage() {

        click(By.linkText("home page"));
    }
    private void initContactModificationById(int id){
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

//        wd.findElement(By.xpath(String.format("//input[value='%s']/../../td[8]/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href=edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {

        click(By.name("update"));
    }

    public int getContactCount() {

        return wd.findElements(By.name("selected[]")).size();
    }

    public void selectContact(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact() {

        click(By.cssSelector("input[value='Delete']"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void closeAlert() {

        wd.switchTo().alert().accept();
    }


    private void selectContactByName(int id) {
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void createContact(ContactData contactData, boolean b) {
        initContactCreation();
        fillContactForm(
                new ContactData().withFirstName("test_name").withLastName("last_name").withGroup("test1"), true);
        submitContactCreation();
        returnHomePage();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(name));
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();

            contactCache.add(new ContactData()
                            .withId(id)
                            .withFirstName(firstname)
                            .withLastName(lastname)
                            .withAddress(address)
                            .withAllPhones(allPhones)
                            .withAllEmails(allEmails)
            );
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstname)
                .withLastName(lastname)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withEmail1(email1)
                .withEmail2(email2)
                .withEmail3(email3)
                .withAddress(address);
    }

    public void modify(ContactData contact) {
        initContactModificationId(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        homePage();
    }
    private void initContactModificationId(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public void create(ContactData contact) {
        initContactCreation();
        contactPage();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        homePage();
    }

}



