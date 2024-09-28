package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Contact;

import static org.junit.jupiter.api.Assertions.*;


    @SpringBootTest
    @TestMethodOrder(MethodOrderer.MethodName.class)
    class ContactFactoryTest {

        @Test
        @Order(1)
        public void a_testCreateContact_SuccessfulBuild() {
           String validEmail = "shane@gmail.com";
            String validPhoneNumber = "1234567890";

            Contact contact = ContactFactory.createContact(validEmail, null);

            assertNotNull(contact);
         assertEquals(validEmail, contact.getEmail());
//          assertEquals(validPhoneNumber, contact.getPhoneNumber());
            System.out.println(contact);
        }

        @Test
        @Order(2)
        public void b_testEmail_Failed() {
            String invalidEmail = "invalidemail.com";
            String emptyPhoneNumber = "+27789456123";

            Contact contact = ContactFactory.createContact(invalidEmail, emptyPhoneNumber);

            assertNotNull(contact);
            System.out.println("Test failed");
            System.out.println(contact);
        }
        @Test
        @Order(3)
        public void c_testPhoneNumber_Failed() {
            String email = "invalide@gmail.com";
            String invalidPhoneNumber = "+277894";

            Contact contact = ContactFactory.createContact(email, invalidPhoneNumber);

            assertNotNull(contact);
            System.out.println("Test failed");
            System.out.println(contact);
        }
    }
