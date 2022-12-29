import React from 'react'
import "./Seat.css"

const Seat = (props) => {
const busy = props.busy;

  return (
    <div>
    {busy===false && <button className='seat empty'  onClick={(value) => {
      
      
    }} value={props.children}>{props.children}</button>}
    {busy===true && <button className='seat busy'>{props.children}</button>}
    </div>
  );
}


export default Seat