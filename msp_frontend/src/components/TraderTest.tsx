import React, {useState} from "react";
import {GenericTrader} from "./trader/GenericTrader";
import { Button } from "@mui/material";

export default function TraderTest() {

  // This is a test component to exemplify how to implement a trader

  const [isTraderMenuOpen, setIsTraderMenuOpen] = useState<boolean>(false);

  return (
    <div>
      <Button onClick={() => {setIsTraderMenuOpen(true)}}>Trader</Button>
      <GenericTrader isOpen={isTraderMenuOpen} setIsOpen={setIsTraderMenuOpen} type={"market"} traderId={0} />
    </div>
  )
}
