DELETE T1_

FROM S2session as T1_

WHERE T1_.LAST_ACCESS < /*expiredDatetime*/1;