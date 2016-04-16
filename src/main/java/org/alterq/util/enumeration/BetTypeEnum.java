package org.alterq.util.enumeration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public enum BetTypeEnum implements Serializable {
	BET_NORMAL(0), BET_FINAL(10), BET_AUTOMATIC(20), BET_RESULT(30), BET_FIXED(40);

	private int value;

	private static Map<Integer, BetTypeEnum> map = new HashMap<Integer, BetTypeEnum>();

	static {
		for (BetTypeEnum legEnum : BetTypeEnum.values()) {
			map.put(legEnum.value, legEnum);
		}
	}

	private BetTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static BetTypeEnum getBetType(String value) {
		if (StringUtils.isNumeric(value)) {
			Integer valor = new Integer(value);
			return map.get(valor);
		} else {
			return BET_NORMAL;
		}
	}
}
