package ua.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.stqa.pft.addressbook.model.ContactData;

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

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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

    public void initContactModification() {

        click(By.cssSelector("img[alt='Edit']"));
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
                new ContactData("test_name", "last_name","test1"),true);
        submitContactCreation();
        returnHomePage();
    }
}
