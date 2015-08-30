package com.packtpub.wflydevelopment.chapter5.control;

import com.packtpub.wflydevelopment.chapter5.entity.Seat;

import javax.ejb.Stateless;

/**
 * @author Yoshimasa Tanabe
 */
@Stateless
public class SeatDao extends AbstractDao<Seat> {

  public SeatDao() {
    super(Seat.class);
  }

}
