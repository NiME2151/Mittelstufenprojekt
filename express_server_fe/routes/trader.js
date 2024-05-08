const express = require('express')
const app = express()
const port = 2151
const router = express.Router();

/* GET users listing. */
router.get('/market_items', function(req, res, next) {
  res.send(
    [
      {
        description: "Ein Appel",
        displayName: "Ein Apfel1",
        itemID: "Apple",
        itemType: "Consumable",
        rarity: "Common",
        standardValue: 10,
        marketValue: 15
      },
      {
        description: "Ein Appel",
        displayName: "Ein Apfel2",
        itemID: "Apple",
        itemType: "Consumable",
        rarity: "Common",
        standardValue: 10,
        marketValue: 13
      }
    ]
  );
});

/*
 private _description: string;
  private _displayName: string;
  private _itemID: number;
  private _itemType: ItemType;
  private _rarity: Rarity;
  private _standardValue: number;
  private _marketValue: number;
*/

module.exports = router;
