package de.szut.msp_backend.dtos;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CharacterTradeRequestDto
{
    private int itemID;
    private int price;
    private String traderID;
}
