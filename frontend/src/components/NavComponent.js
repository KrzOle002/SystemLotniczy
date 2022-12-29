import React from 'react'
import {Link} from "react-router-dom";
import "./NavComponent.css"

const NavComponent = () => {
  return (
    <div className='nav'>
    <p className='logo'>PBS Airport</p>
    <Link to="/"><p className='nav-font'>Home</p></Link>
    <Link to="/add"><p className='nav-font'>Reservation</p></Link>
    <Link to="/reservations"><p className='nav-font'>Reservations List</p></Link>
    </div>  
  );
}

export default NavComponent