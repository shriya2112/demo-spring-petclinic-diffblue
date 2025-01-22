package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;

import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@ContextConfiguration(classes = {PetController.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class PetControllerDiffblueTest {
    @MockitoBean
    private OwnerRepository ownerRepository;

    @Autowired
    private PetController petController;

    @MockitoBean
    private PetTypeRepository petTypeRepository;

    /**
     * Test {@link PetController#initCreationForm(Owner, ModelMap)}.
     *
     * <p>Method under test: {@link PetController#initCreationForm(Owner, ModelMap)}
     */
    @Test
    @DisplayName("Test initCreationForm(Owner, ModelMap)")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({"String PetController.initCreationForm(Owner, ModelMap)"})
    void testInitCreationForm() throws Exception {
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
        when(petTypeRepository.findPetTypes()).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/owners/{ownerId}/pets/new", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(petController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("owner", "pet", "types"))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
    }

    /**
     * Test {@link PetController#processCreationForm(Owner, Pet, BindingResult, RedirectAttributes)}.
     *
     * <ul>
     *   <li>When {@code 2020-03-01}.
     * </ul>
     *
     * <p>Method under test: {@link PetController#processCreationForm(Owner, Pet, BindingResult,
     * RedirectAttributes)}
     */
    @Test
    @DisplayName(
            "Test processCreationForm(Owner, Pet, BindingResult, RedirectAttributes); when '2020-03-01'")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({
            "String PetController.processCreationForm(Owner, Pet, BindingResult, RedirectAttributes)"
    })
    void testProcessCreationForm_when20200301() throws Exception {
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
        when(petTypeRepository.findPetTypes()).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 1)
                        .param("name", "not blank")
                        .param("birthDate", "2020-03-01");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(petController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("owner", "pet", "types"))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
    }

    /**
     * Test {@link PetController#processCreationForm(Owner, Pet, BindingResult, RedirectAttributes)}.
     *
     * <ul>
     *   <li>When empty string.
     * </ul>
     *
     * <p>Method under test: {@link PetController#processCreationForm(Owner, Pet, BindingResult,
     * RedirectAttributes)}
     */
    @Test
    @DisplayName(
            "Test processCreationForm(Owner, Pet, BindingResult, RedirectAttributes); when empty string")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({
            "String PetController.processCreationForm(Owner, Pet, BindingResult, RedirectAttributes)"
    })
    void testProcessCreationForm_whenEmptyString() throws Exception {
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
        when(petTypeRepository.findPetTypes()).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 1).param("name", "");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(petController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("owner", "pet", "types"))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
    }

    /**
     * Test {@link PetController#processCreationForm(Owner, Pet, BindingResult, RedirectAttributes)}.
     *
     * <ul>
     *   <li>When {@code not blank}.
     * </ul>
     *
     * <p>Method under test: {@link PetController#processCreationForm(Owner, Pet, BindingResult,
     * RedirectAttributes)}
     */
    @Test
    @DisplayName(
            "Test processCreationForm(Owner, Pet, BindingResult, RedirectAttributes); when 'not blank'")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({
            "String PetController.processCreationForm(Owner, Pet, BindingResult, RedirectAttributes)"
    })
    void testProcessCreationForm_whenNotBlank() throws Exception {
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
        when(petTypeRepository.findPetTypes()).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 1).param("name", "not blank");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(petController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("owner", "pet", "types"))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
    }

    /**
     * Test {@link PetController#initUpdateForm()}.
     *
     * <p>Method under test: {@link PetController#initUpdateForm()}
     */
    @Test
    @DisplayName("Test initUpdateForm()")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({"String PetController.initUpdateForm()"})
    void testInitUpdateForm() throws Exception {
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
        when(petTypeRepository.findPetTypes()).thenReturn(new ArrayList<>());

        // Act and Assert
        MockMvcBuilders.standaloneSetup(petController)
                .build()
                .perform(MockMvcRequestBuilders.get("/owners/{ownerId}/pets/{petId}/edit", 1, 1))
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("owner", "types"))
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
    }

    /**
     * Test {@link PetController#processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)}.
     *
     * <ul>
     *   <li>Given empty string.
     *   <li>When {@link Pet} (default constructor) Name is empty string.
     * </ul>
     *
     * <p>Method under test: {@link PetController#processUpdateForm(Owner, Pet, BindingResult,
     * RedirectAttributes)}
     */
    @Test
    @DisplayName(
            "Test processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes); given empty string; when Pet (default constructor) Name is empty string")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({
            "String PetController.processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)"
    })
    void testProcessUpdateForm_givenEmptyString_whenPetNameIsEmptyString() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
        //   Run dcover create --keep-partial-tests to gain insights into why
        //   a non-Spring test was created.

        // Arrange
        Owner owner = new Owner();
        owner.setAddress("42 Main St");
        owner.setCity("Oxford");
        owner.setFirstName("Jane");
        owner.setId(1);
        owner.setLastName("Doe");
        owner.setTelephone("6625550144");

        OwnerRepository owners = mock(OwnerRepository.class);
        when(owners.save(Mockito.<Owner>any())).thenReturn(owner);
        PetController petController = new PetController(owners, mock(PetTypeRepository.class));

        Owner owner2 = new Owner();
        owner2.setAddress("42 Main St");
        owner2.setCity("Oxford");
        owner2.setFirstName("Jane");
        owner2.setId(1);
        owner2.setLastName("Doe");
        owner2.setTelephone("6625550144");

        PetType type = new PetType();
        type.setId(1);
        type.setName("Dog");

        Pet pet = new Pet();
        pet.setBirthDate(LocalDate.of(1970, 1, 1));
        pet.setId(1);
        pet.setName("");
        pet.setType(type);
        BindException result = new BindException("Target", "Object Name");

        // Act
        String actualProcessUpdateFormResult =
                petController.processUpdateForm(owner2, pet, result, new RedirectAttributesModelMap());

        // Assert
        verify(owners).save(isA(Owner.class));
        assertEquals("redirect:/owners/{ownerId}", actualProcessUpdateFormResult);
    }

    /**
     * Test {@link PetController#processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)}.
     *
     * <ul>
     *   <li>Given {@code null}.
     *   <li>When {@link Pet} (default constructor) BirthDate is {@code null}.
     * </ul>
     *
     * <p>Method under test: {@link PetController#processUpdateForm(Owner, Pet, BindingResult,
     * RedirectAttributes)}
     */
    @Test
    @DisplayName(
            "Test processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes); given 'null'; when Pet (default constructor) BirthDate is 'null'")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({
            "String PetController.processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)"
    })
    void testProcessUpdateForm_givenNull_whenPetBirthDateIsNull() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
        //   Run dcover create --keep-partial-tests to gain insights into why
        //   a non-Spring test was created.

        // Arrange
        Owner owner = new Owner();
        owner.setAddress("42 Main St");
        owner.setCity("Oxford");
        owner.setFirstName("Jane");
        owner.setId(1);
        owner.setLastName("Doe");
        owner.setTelephone("6625550144");

        OwnerRepository owners = mock(OwnerRepository.class);
        when(owners.save(Mockito.<Owner>any())).thenReturn(owner);
        PetController petController = new PetController(owners, mock(PetTypeRepository.class));

        Owner owner2 = new Owner();
        owner2.setAddress("42 Main St");
        owner2.setCity("Oxford");
        owner2.setFirstName("Jane");
        owner2.setId(1);
        owner2.setLastName("Doe");
        owner2.setTelephone("6625550144");

        PetType type = new PetType();
        type.setId(1);
        type.setName("Dog");

        Pet pet = new Pet();
        pet.setBirthDate(null);
        pet.setId(1);
        pet.setName("Bella");
        pet.setType(type);
        BindException result = new BindException("Target", "Object Name");

        // Act
        String actualProcessUpdateFormResult =
                petController.processUpdateForm(owner2, pet, result, new RedirectAttributesModelMap());

        // Assert
        verify(owners).save(isA(Owner.class));
        assertEquals("redirect:/owners/{ownerId}", actualProcessUpdateFormResult);
    }

    /**
     * Test {@link PetController#processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)}.
     *
     * <ul>
     *   <li>Given {@code true}.
     *   <li>Then return {@code pets/createOrUpdatePetForm}.
     * </ul>
     *
     * <p>Method under test: {@link PetController#processUpdateForm(Owner, Pet, BindingResult,
     * RedirectAttributes)}
     */
    @Test
    @DisplayName(
            "Test processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes); given 'true'; then return 'pets/createOrUpdatePetForm'")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({
            "String PetController.processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)"
    })
    void testProcessUpdateForm_givenTrue_thenReturnPetsCreateOrUpdatePetForm() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
        //   Run dcover create --keep-partial-tests to gain insights into why
        //   a non-Spring test was created.

        // Arrange
        PetController petController =
                new PetController(mock(OwnerRepository.class), mock(PetTypeRepository.class));

        Owner owner = new Owner();
        owner.setAddress("42 Main St");
        owner.setCity("Oxford");
        owner.setFirstName("Jane");
        owner.setId(1);
        owner.setLastName("Doe");
        owner.setTelephone("6625550144");

        PetType type = new PetType();
        type.setId(1);
        type.setName("Dog");

        Pet pet = new Pet();
        pet.setBirthDate(LocalDate.of(1970, 1, 1));
        pet.setId(1);
        pet.setName("Bella");
        pet.setType(type);

        BeanPropertyBindingResult result = mock(BeanPropertyBindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // Act
        String actualProcessUpdateFormResult =
                petController.processUpdateForm(owner, pet, result, new RedirectAttributesModelMap());

        // Assert
        verify(result).hasErrors();
        assertEquals("pets/createOrUpdatePetForm", actualProcessUpdateFormResult);
    }

    /**
     * Test {@link PetController#processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)}.
     *
     * <ul>
     *   <li>Then return {@code redirect:/owners/{ownerId}}.
     * </ul>
     *
     * <p>Method under test: {@link PetController#processUpdateForm(Owner, Pet, BindingResult,
     * RedirectAttributes)}
     */
    @Test
    @DisplayName(
            "Test processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes); then return 'redirect:/owners/{ownerId}'")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({
            "String PetController.processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)"
    })
    void testProcessUpdateForm_thenReturnRedirectOwnersOwnerId() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
        //   Run dcover create --keep-partial-tests to gain insights into why
        //   a non-Spring test was created.

        // Arrange
        Owner owner = new Owner();
        owner.setAddress("42 Main St");
        owner.setCity("Oxford");
        owner.setFirstName("Jane");
        owner.setId(1);
        owner.setLastName("Doe");
        owner.setTelephone("6625550144");

        OwnerRepository owners = mock(OwnerRepository.class);
        when(owners.save(Mockito.<Owner>any())).thenReturn(owner);
        PetController petController = new PetController(owners, mock(PetTypeRepository.class));

        Owner owner2 = new Owner();
        owner2.setAddress("42 Main St");
        owner2.setCity("Oxford");
        owner2.setFirstName("Jane");
        owner2.setId(1);
        owner2.setLastName("Doe");
        owner2.setTelephone("6625550144");

        PetType type = new PetType();
        type.setId(1);
        type.setName("Dog");

        Pet pet = new Pet();
        pet.setBirthDate(LocalDate.of(1970, 1, 1));
        pet.setId(1);
        pet.setName("Bella");
        pet.setType(type);
        BindException result = new BindException("Target", "Object Name");

        // Act
        String actualProcessUpdateFormResult =
                petController.processUpdateForm(owner2, pet, result, new RedirectAttributesModelMap());

        // Assert
        verify(owners).save(isA(Owner.class));
        assertEquals("redirect:/owners/{ownerId}", actualProcessUpdateFormResult);
    }

    /**
     * Test {@link PetController#processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)}.
     *
     * <ul>
     *   <li>Then throw {@link IllegalArgumentException}.
     * </ul>
     *
     * <p>Method under test: {@link PetController#processUpdateForm(Owner, Pet, BindingResult,
     * RedirectAttributes)}
     */
    @Test
    @DisplayName(
            "Test processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes); then throw IllegalArgumentException")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({
            "String PetController.processUpdateForm(Owner, Pet, BindingResult, RedirectAttributes)"
    })
    void testProcessUpdateForm_thenThrowIllegalArgumentException() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
        //   Run dcover create --keep-partial-tests to gain insights into why
        //   a non-Spring test was created.

        // Arrange
        OwnerRepository owners = mock(OwnerRepository.class);
        when(owners.save(Mockito.<Owner>any())).thenThrow(new IllegalArgumentException());
        PetController petController = new PetController(owners, mock(PetTypeRepository.class));

        Owner owner = new Owner();
        owner.setAddress("42 Main St");
        owner.setCity("Oxford");
        owner.setFirstName("Jane");
        owner.setId(1);
        owner.setLastName("Doe");
        owner.setTelephone("6625550144");

        PetType type = new PetType();
        type.setId(1);
        type.setName("Dog");

        Pet pet = new Pet();
        pet.setBirthDate(LocalDate.of(1970, 1, 1));
        pet.setId(1);
        pet.setName("Bella");
        pet.setType(type);
        BindException result = new BindException("Target", "Object Name");

        // Act and Assert
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        petController.processUpdateForm(owner, pet, result, new RedirectAttributesModelMap()));
        verify(owners).save(isA(Owner.class));
    }
}
