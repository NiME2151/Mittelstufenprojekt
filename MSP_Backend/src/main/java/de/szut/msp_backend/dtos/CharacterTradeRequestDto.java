package de.szut.msp_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
