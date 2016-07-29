package org.alterq.domain.test;

import java.util.UUID;

import org.alterq.domain.AdminData;
import org.alterq.domain.Company;
import org.alterq.domain.SequenceId;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.CompanyDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.RoundRankingDao;
import org.alterq.repo.SequenceIdDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.repo.impl.AdminDataDaoImpl;
import org.alterq.repo.impl.CompanyDaoImpl;
import org.alterq.repo.impl.RoundBetDaoImpl;
import org.alterq.repo.impl.RoundDaoImpl;
import org.alterq.repo.impl.RoundRankingDaoImpl;
import org.alterq.repo.impl.SequenceIdDaoImpl;
import org.alterq.repo.impl.SessionAlterQDaoImpl;
import org.alterq.repo.impl.UserAlterQDaoImpl;
import org.alterq.util.enumeration.CompanyTypeEnum;
import org.alterq.util.enumeration.SequenceNameEnum;
import org.apache.commons.lang3.StringUtils;
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
	int season = 2015;
	int round = 1;
	float priceBet=0.75f;
	String idDelegacion="12345";
	String idReceptor="12";
	
	@Autowired
	private UserAlterQDao userAlterQDao;
	@Autowired
	private SessionAlterQDao sessionAlterQDao;
	@Autowired
	private RoundRankingDao roundRankingDao;
	@Autowired
	private RoundBetDao roundBetDao;
	@Autowired
	private RoundDao roundDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private SequenceIdDao sequenceDao;
	@Autowired
	private AdminDataDao adminDataDao;

//	@Test
	public void test00CreateCollection() throws Exception {
		createCollectionAdmin();
		createCollectionCompany();
		createCollectionRound();
		createCollectionRoundBets();
		createCollectionRoundRanking();
		createCollectionSequence();
		createCollectionSessionAlterQ();
		createCollectionUserAlterQ();
		return;
	}
	@Test
	public void test01Create() throws Exception {
//		createAdminData();
//		createCompanyData();
		createSequenceData();
		return;
	}

	public void createCollectionCompany() throws Exception {
		((CompanyDaoImpl) companyDao).createCollection();
		log.debug("CreateCollectionCompany:");
		return;
	}
	public void createCollectionAdmin() throws Exception {
		((AdminDataDaoImpl) adminDataDao).createCollection();
		log.debug("CreateCollectionAdmin:");
		return;
	}
	private void createCollectionSequence() {
		((SequenceIdDaoImpl) sequenceDao).createCollection();
		log.debug("CreateCollectionSequence:");
		return;
	}
	private void createCollectionRound() {
		((RoundDaoImpl) roundDao).createCollection();
		log.debug("CreateCollectionround:");
		return;
	}
	private void createCollectionUserAlterQ() {
		((UserAlterQDaoImpl) userAlterQDao).createCollection();
		log.debug("CreateCollectionUserAlterQ:");
		return;
	}
	private void createCollectionSessionAlterQ() {
		((SessionAlterQDaoImpl) sessionAlterQDao).createCollection();
		log.debug("CreateCollectionRoundRanking:");
		return;
	}
	private void createCollectionRoundRanking() {
		((RoundRankingDaoImpl) roundRankingDao).createCollection();
		log.debug("CreateCollectionRoundRanking:");
		return;
	}
	private void createCollectionRoundBets() {
		((RoundBetDaoImpl) roundBetDao).createCollection();
		log.debug("CreateCollectionRoundBets:");
		return;
	}

	public void createAdminData() throws Exception {
		AdminData bean = new AdminData();
		bean.setCompany(AlterQConstants.DEFECT_ADMINDATA);
		bean.setRound(round);
		bean.setSeason(season);
		bean.setIdDelegacion(idDelegacion);
		bean.setIdReceptor(idReceptor);
		bean.setActive(Boolean.TRUE);
		bean.setPrizeBet(priceBet);
		adminDataDao.add(bean);
		Assert.assertNotNull(bean.getIdDelegacion());
		log.debug("Create:" + bean.getCompany() + "-" + bean.getIdDelegacion());
		return;
	}

	public void createCompanyData() throws Exception {
		Company bean = new Company();
		bean.setCompany(AlterQConstants.DEFECT_COMPANY);
		bean.setDescription("QuiniGold");
		bean.setNick("QuiniGold");
		bean.setType(CompanyTypeEnum.COMPANY_NON_COLLABORATIVE_PUBLIC.getValue());
		bean.setVisibility(Boolean.TRUE);
		companyDao.add(bean);
		Assert.assertNotNull(bean.getDescription());
		log.debug("Create:" + bean.getCompany() + "-" + bean.getDescription());
		
//		SequenceId beanSeq = daoSequence.findById(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
//		int seq = daoSequence.getNextSequenceId(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
//		
//		bean=new Company();
//		bean.setCompany(seq);
//		bean.setDescription("QuiniGold2");
//		bean.setNick("QuiniGold2");
//		bean.setType(CompanyTypeEnum.COMPANY_NON_COLLABORATIVE.getValue());
//		bean.setVisibility(Boolean.TRUE);
//		companyDao.add(bean);
//		Assert.assertNotNull(bean.getDescription());
//		log.debug("Create:" + bean.getCompany() + "-" + bean.getDescription());
		return;
	}
	private void createSequenceData() {
		SequenceId sequence=new SequenceId();
		sequence.setId(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
		sequence.setSequence("21");
		sequenceDao.add(sequence);
		SequenceId sequenceO=new SequenceId();
		sequenceO.setId(SequenceNameEnum.SEQUENCE_OTHER.getValue());
		sequenceO.setSequence("0");
		sequenceDao.add(sequenceO);
		SequenceId sequence1=new SequenceId();
		sequence1.setId(SequenceNameEnum.SECRET_KEY.getValue());
		sequence1.setSequence(StringUtils.remove(UUID.randomUUID().toString(), "-") );
		sequenceDao.add(sequence1);
	}

}
