package com.coupond.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.coupond.enitity.Coupon;

@Component
public class UserModelListener extends AbstractMongoEventListener<Coupon> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public UserModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Coupon> event) {
        if (event.getSource().getCouponId() < 1) {
            event.getSource().setCouponId(sequenceGenerator.generateSequence(Coupon.SEQUENCE_NAME));
        }
    }


}
