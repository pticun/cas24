package org.alterq.util.enumeration;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;

@RunWith(BlockJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BetReductionTypeEnumTest {

	@Test
	public void testBetReductionType() throws Exception {
		int reductionType = BetReductionTypeEnum.BET_REDUCTION_NONE.getValue();
		reductionType=-45;
		BetReductionTypeEnum type = BetReductionTypeEnum.BET_REDUCTION_ERROR;
		type.setValue(reductionType);
		Assert.assertTrue(BetReductionTypeEnum.BET_REDUCTION_NONE.getValue() == type.getValue());
	}

}
