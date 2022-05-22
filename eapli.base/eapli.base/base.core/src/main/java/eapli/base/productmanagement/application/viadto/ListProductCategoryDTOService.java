package eapli.base.productmanagement.application.viadto;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.dto.ProductCategoryDTO;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ApplicationService
public class ListProductCategoryDTOService {

}
