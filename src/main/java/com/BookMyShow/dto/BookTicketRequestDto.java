/**
 * 
 */
package com.BookMyShow.dto;


import com.BookMyShow.enums.SeatType;
import lombok.*;

import java.util.Set;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class BookTicketRequestDto {


	private Set<String> seatsNumbers;


	private long userId;


	private long showId;


	private SeatType seatType;

}