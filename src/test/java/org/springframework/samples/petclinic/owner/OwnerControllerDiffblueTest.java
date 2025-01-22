package org.springframework.samples.petclinic.owner;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {OwnerController.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class OwnerControllerDiffblueTest {
    @Autowired
    private OwnerController ownerController;

    @MockitoBean
    private OwnerRepository ownerRepository;

    /**
     * Test {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}.
     *
     * <ul>
     *   <li>Given {@link Owner} (default constructor) Address is {@code 17 High St}.
     *   <li>Then model size five.
     * </ul>
     *
     * <p>Method under test: {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}
     */
    @Test
    @DisplayName(
            "Test processFindForm(int, Owner, BindingResult, Model); given Owner (default constructor) Address is '17 High St'; then model size five")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({"String OwnerController.processFindForm(int, Owner, BindingResult, Model)"})
    void testProcessFindForm_givenOwnerAddressIs17HighSt_thenModelSizeFive() throws Exception {
        // Arrange
        Owner owner = new Owner();
        owner.setAddress("42 Main St");
        owner.setCity("Oxford");
        owner.setFirstName("Jane");
        owner.setId(1);
        owner.setLastName("Doe");
        owner.setTelephone("6625550144");

        Owner owner2 = new Owner();
        owner2.setAddress("17 High St");
        owner2.setCity("London");
        owner2.setFirstName("John");
        owner2.setId(2);
        owner2.setLastName("Smith");
        owner2.setTelephone("8605550118");

        ArrayList<Owner> content = new ArrayList<>();
        content.add(owner2);
        content.add(owner);
        when(ownerRepository.findByLastNameStartingWith(Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(content));

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/owners").param("page", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ownerController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().size(5))
                .andExpect(
                        model()
                                .attributeExists("currentPage", "listOwners", "owner", "totalItems", "totalPages"))
                .andExpect(view().name("owners/ownersList"))
                .andExpect(forwardedUrl("owners/ownersList"));
    }

    /**
     * Test {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}.
     *
     * <ul>
     *   <li>Given {@link Owner} (default constructor) Address is {@code 42 Main St}.
     *   <li>Then status {@link StatusResultMatchers#isFound()}.
     * </ul>
     *
     * <p>Method under test: {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}
     */
    @Test
    @DisplayName(
            "Test processFindForm(int, Owner, BindingResult, Model); given Owner (default constructor) Address is '42 Main St'; then status isFound()")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({"String OwnerController.processFindForm(int, Owner, BindingResult, Model)"})
    void testProcessFindForm_givenOwnerAddressIs42MainSt_thenStatusIsFound() throws Exception {
        // Arrange
        Owner owner = new Owner();
        owner.setAddress("42 Main St");
        owner.setCity("Oxford");
        owner.setFirstName("Jane");
        owner.setId(1);
        owner.setLastName("Doe");
        owner.setTelephone("6625550144");

        ArrayList<Owner> content = new ArrayList<>();
        content.add(owner);
        when(ownerRepository.findByLastNameStartingWith(Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(content));

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/owners").param("page", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ownerController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isFound())
                .andExpect(model().size(0))
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(redirectedUrl("/owners/1"));
    }

    /**
     * Test {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}.
     *
     * <ul>
     *   <li>When {@code Doe}.
     *   <li>Then model size one.
     * </ul>
     *
     * <p>Method under test: {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}
     */
    @Test
    @DisplayName(
            "Test processFindForm(int, Owner, BindingResult, Model); when 'Doe'; then model size one")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({"String OwnerController.processFindForm(int, Owner, BindingResult, Model)"})
    void testProcessFindForm_whenDoe_thenModelSizeOne() throws Exception {
        // Arrange
        when(ownerRepository.findByLastNameStartingWith(Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/owners")
                        .param("page", String.valueOf(1))
                        .param("lastName", "Doe");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ownerController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/findOwners"))
                .andExpect(forwardedUrl("owners/findOwners"));
    }

    /**
     * Test {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}.
     *
     * <ul>
     *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code page} is
     *       valueOf one.
     *   <li>Then model size one.
     * </ul>
     *
     * <p>Method under test: {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}
     */
    @Test
    @DisplayName(
            "Test processFindForm(int, Owner, BindingResult, Model); when param(String, String[]) 'page' is valueOf one; then model size one")
    @Tag("ContributionFromDiffblue")
    @ManagedByDiffblue
    @MethodsUnderTest({"String OwnerController.processFindForm(int, Owner, BindingResult, Model)"})
    void testProcessFindForm_whenParamPageIsValueOfOne_thenModelSizeOne() throws Exception {
        // Arrange
        when(ownerRepository.findByLastNameStartingWith(Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/owners").param("page", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ownerController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/findOwners"))
                .andExpect(forwardedUrl("owners/findOwners"));
    }
}
