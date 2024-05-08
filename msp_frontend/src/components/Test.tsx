import React, {useState} from "react";
import {GenericTrader} from "./trader/GenericTrader";

export const Test = () => {

  const [isTraderMenuOpen, setIsTraderMenuOpen] = useState<boolean>(false);

  return (
    <div>
      <button onClick={() => {setIsTraderMenuOpen(true)}}>Trader</button>
      <GenericTrader isOpen={isTraderMenuOpen} setIsOpen={setIsTraderMenuOpen} type={"market"} />
    </div>
  )
}
