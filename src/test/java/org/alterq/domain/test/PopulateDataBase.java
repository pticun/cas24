package org.alterq.domain.test;

import java.util.ArrayList;
import java.util.List;

import org.alterq.domain.AdminData;
import org.alterq.domain.Bet;
import org.alterq.domain.Company;
import org.alterq.domain.Game;
import org.alterq.domain.GeneralData;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.CompanyDao;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SequenceIdDao;
import org.alterq.repo.impl.AdminDataDaoImpl;
import org.alterq.repo.impl.CompanyDaoImpl;
import org.alterq.util.enumeration.CompanyTypeEnum;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PopulateDataBase {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	int season = 2014;
	int round = 12;
	int company=1;
	float price=0.5f;
	
	private String[] games1 = { "GETAFE", "VALENCIA", "AT. MADRID", "ATHLETIC CLUB", "LEVANTE", "GRANADA", "SEVILLA", "CELTA", "RAYO VALLECANO", "R. MADRID",
			"R. SOCIEDAD", "OSASUNA", "ALMERÍA", "VALLADOLID", "MALLORCA" };
	private String[] games2 = { "LUGO", "PONFERRADINA", "RECREATIVO", "EIBAR", "ZARAGOZA", "JAEN", "MIRANDÉS", "GIRONA", "HÉRCULES", "LAS PALMAS", "ALCORCÓN",
			"CÓRDOBA", "SPORTING", "MÁLAGA", "BETIS" };

	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private SequenceIdDao daoSequence;
	private AdminDataDao dao;

	@Test
	public void test00CreateCollection() throws Exception {
		createCollectionAdmin();
		createCollectionCompany();
		return;
	}
	@Test
	public void test01Create() throws Exception {
		createAdminData();
		createCompanyData();
		return;
	}

	public void createCollectionCompany() throws Exception {
		((CompanyDaoImpl) companyDao).createCollection();
		log.debug("CreateCollection:");
		return;
	}
	public void createCollectionAdmin() throws Exception {
		((AdminDataDaoImpl) dao).createCollection();
		log.debug("CreateCollection:");
		return;
	}

	public void createAdminData() throws Exception {
		AdminData bean = new AdminData();
		bean.setCompany(AlterQConstants.ADMIN_COMPANY);
		bean.setRound(1);
		bean.setSeason(2015);
		bean.setIdDelegacion("12345");
		bean.setIdReceptor("12");
		dao.add(bean);
		Assert.assertNotNull(bean.getIdDelegacion());
		log.debug("Create:" + bean.getCompany() + "-" + bean.getIdDelegacion());
		return;
	}

	public void createCompanyData() throws Exception {
		Company bean = new Company();
		bean.setCompany(AlterQConstants.DEFECT_COMPANY);
		bean.setDescription("description");
		bean.setNick("Nich");
		bean.setType(CompanyTypeEnum.COMPANY_NON_COLLABORATIVE.getValue());
		bean.setVisibility(Boolean.TRUE);
		companyDao.add(bean);
		Assert.assertNotNull(bean.getDescription());
		log.debug("Create:" + bean.getCompany() + "-" + bean.getDescription());
		return;
	}

}
