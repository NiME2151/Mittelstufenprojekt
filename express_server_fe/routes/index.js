const express = require("express");
const cors = require('cors')
const app = express()

app.use(cors())
const router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});


router.get('/api/trader/market_items', cors(), function(req, res, next) {
  res.send(
    [
      {
        description: "An apple",
        displayName: "Apple",
        itemID: "Apple",
        itemType: "Consumable",
        rarity: "Common",
        standardValue: 20,
        marketBuyValue: 25,
        marketSellValue: 15
      },
      {
        description: "An apple",
        displayName: "Apple",
        itemID: "Apple",
        itemType: "Consumable",
        rarity: "Common",
        standardValue: 20,
        marketBuyValue: 23,
        marketSellValue: 13
      }
    ]
  );
});

router.get('/api/character/trade_inventory', cors(), function(req, res, next) {
  res.send(
    [
      {
        description: "A bread",
        displayName: "Bread",
        itemID: "Bread",
        itemType: "Consumable",
        rarity: "Common",
        standardValue: 10,
        marketBuyValue: 15,
        marketSellValue: 5
      },
      {
        description: "An apple",
        displayName: "Apple",
        itemID: "Apple",
        itemType: "Consumable",
        rarity: "Common",
        standardValue: 10,
        marketBuyValue: 13,
        marketSellValue: 3
      }
    ]
  );
});

router.get('/api/trader', cors(), function(req, res, next) {
  res.send(
    {
      name: "Lynn the Smith",
      money: 1000,
      inventory: []
    }
  );
});

router.get('/api/trader/test', cors(), function(req, res, next) {
  res.send(
    {
      name: "Lynn the Smith 2",
      money: 900,
      inventory: []
    }
  );
});

router.post('/api/trader/buy', cors(), function(req, res, next) {
  res.send(406);
});

router.post('/api/trader/sell', cors(), function(req, res, next) {
  res.send(405);
});

module.exports = router;
