package com.packtpub.wflydevelopment.chapter5.control;

import com.packtpub.wflydevelopment.chapter5.entity.SeatType;

import javax.ejb.Stateless;

/**
 * @author Yoshimasa Tanabe
 */
@Stateless
public class SeatTypeDao extends AbstractDao<SeatType> {

  public SeatTypeDao() {
    super(SeatType.class);
  }

}
