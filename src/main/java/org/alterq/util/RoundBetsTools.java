package org.alterq.util;

import java.lang.Math;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.RoundBetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoundBetsTools{
	@Autowired
	private AdminDataDao adminDataDao;
	
	@Autowired
	private RoundBetDao betDao;

	public int getNumberBets(int company){
		int round = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA).getRound();
		int season = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA).getSeason();
			
		return betDao.countAllBets(season, round, company);
	}

	public int[][] getSummarizeBets (int company)
	{
		int salida[][] = {{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
		
		int round = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA).getRound();
		int season = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA).getSeason();

		RoundBets rBets = betDao.findRoundBetWithBets(season, round, company);
		
		for (Bet bet : rBets.getBets()) {
			String apu = bet.getBet();

			for (int j = 0; j < 15; j++) {
				if (apu.charAt(j) == '4') {
					salida[j][0]++;
				} else if (apu.charAt(j) == '2') {
					salida[j][1]++;
				} else if (apu.charAt(j) == '1') {
					salida[j][2]++;
				} else if (apu.charAt(j) == '6') {
					salida[j][0]++;
					salida[j][1]++;
				} else if (apu.charAt(j) == '5') {
					salida[j][0]++;
					salida[j][2]++;
				} else if (apu.charAt(j) == '3') {
					salida[j][1]++;
					salida[j][2]++;
				} else if (apu.charAt(j) == '7') {
					salida[j][0]++;
					salida[j][1]++;
					salida[j][2]++;
				}
			}
		}

		
		
		return salida;
		
	}	

}