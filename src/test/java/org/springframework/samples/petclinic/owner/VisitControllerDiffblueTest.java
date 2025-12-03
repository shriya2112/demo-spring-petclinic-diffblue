package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ContextConfiguration(classes = {VisitController.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class VisitControllerDiffblueTest {
    @MockitoBean
    private OwnerRepository ownerRepository;

    @Autowired
    private VisitController visitController;

    /**
     * Test {@link VisitController#loadPetWithVisit(int, int, Map)}.
     *
     * <ul>
     *   <li>Given {@link OwnerRepository} {@link OwnerRepository#findById(Integer)} return empty.
     * </ul>
     *
     * <p>Method under test: {@link VisitController#loadPetWithVisit(int, int, Map)}
     */
    @Test
    @DisplayName(
            "Test loadPetWithVisit(int, int, Map); given OwnerRepository findById(Integer) return empty")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({"Visit VisitController.loadPetWithVisit(int, int, Map)"})
    void testLoadPetWithVisit_givenOwnerRepositoryFindByIdReturnEmpty() {
        // Arrange
        Optional<Owner> emptyResult = Optional.empty();
        when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> visitController.loadPetWithVisit(1, 1, new HashMap<>()));
        verify(ownerRepository).findById(1);
    }

    /**
     * Test {@link VisitController#loadPetWithVisit(int, int, Map)}.
     *
     * <ul>
     *   <li>Given {@link OwnerRepository} {@link OwnerRepository#findById(Integer)} throw {@link
     *       IllegalArgumentException#IllegalArgumentException()}.
     * </ul>
     *
     * <p>Method under test: {@link VisitController#loadPetWithVisit(int, int, Map)}
     */
    @Test
    @DisplayName(
            "Test loadPetWithVisit(int, int, Map); given OwnerRepository findById(Integer) throw IllegalArgumentException()")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({"Visit VisitController.loadPetWithVisit(int, int, Map)"})
    void testLoadPetWithVisit_givenOwnerRepositoryFindByIdThrowIllegalArgumentException() {
        // Arrange
        when(ownerRepository.findById(Mockito.<Integer>any()))
                .thenThrow(new IllegalArgumentException());

        // Act and Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> visitController.loadPetWithVisit(1, 1, new HashMap<>()));
        verify(ownerRepository).findById(1);
    }

    /**
     * Test {@link VisitController#initNewVisitForm()}.
     *
     * <p>Method under test: {@link VisitController#initNewVisitForm()}
     */
    @Test
    @DisplayName("Test initNewVisitForm()")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({"java.lang.String VisitController.initNewVisitForm()"})
    void testInitNewVisitForm() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
        //   Run dcover create --keep-partial-tests to gain insights into why
        //   a non-Spring test was created.

        // Arrange, Act and Assert
        assertEquals(
                "pets/createOrUpdateVisitForm",
                new VisitController(mock(OwnerRepository.class)).initNewVisitForm());
    }

    /**
     * Test {@link VisitController#processNewVisitForm(Owner, int, Visit, BindingResult,
     * RedirectAttributes)}.
     *
     * <p>Method under test: {@link VisitController#processNewVisitForm(Owner, int, Visit,
     * BindingResult, RedirectAttributes)}
     */
    @Test
    @DisplayName("Test processNewVisitForm(Owner, int, Visit, BindingResult, RedirectAttributes)")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({
            "java.lang.String VisitController.processNewVisitForm(Owner, int, Visit, BindingResult, RedirectAttributes)"
    })
    void testProcessNewVisitForm() throws Exception {
        // Arrange
        Owner owner = new Owner();
        owner.setAddress("42 Main St");
        owner.setCity("Oxford");
        owner.setFirstName("Jane");
        owner.setId(1);
        owner.setLastName("Doe");
        owner.setTelephone("6625550144");
        Optional<Owner> ofResult = Optional.of(owner);
        when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/owners/{ownerId}/pets/{petId}/visits/new", "Bella", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(visitController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().is(400));
    }
}
