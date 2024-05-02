"use client";

import React, {useState} from 'react'
import RealTimeProductSearch from './RealTimeProductSearch'
import Layout from '../layout'
const page = () => {

  const [search, setSearch] = useState({search: ''})
  const [data, setData] = useState('')

  const loadData = async (search) => {
    setData(RealTimeProductSearch(search));
  }


  const handleChange = (event) => {
    const {name, value} = event.target;
    setSearch(prevSearch => ({ ...prevSearch, [name]: value}));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    loadData(search.search);
  }

  return (
    <Layout>
      <form onSubmit={handleSubmit} className="apiSearchBar">
      <input
        type="text"
        name="search"
        value={search.search}
        onChange={handleChange}
        placeholder="Search Average Price:"
      />
      <button type="submit">Submit</button>
      </form>
      { data !== "" ? <p>{data}</p> : <></>}
    </Layout>
  )
}

export default page