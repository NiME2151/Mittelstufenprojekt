import React, {useState} from "react";
import {GenericTrader} from "./trader/GenericTrader";
import { Button } from "@mui/material";

export default function Trader() {

  const [isTraderMenuOpen, setIsTraderMenuOpen] = useState<boolean>(false);

  return (
    <div>
      <Button onClick={() => {setIsTraderMenuOpen(true)}}>Trader</Button>
      <GenericTrader isOpen={isTraderMenuOpen} setIsOpen={setIsTraderMenuOpen} type={"market"} />
    </div>
  )
}
