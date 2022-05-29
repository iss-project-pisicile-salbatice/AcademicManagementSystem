import React from 'react'
import ResultItem from './ResultItem'
const ResultList = ({results}) => {
  return (
    <div>{results.map((result) => (<ResultItem result={result}/>))}</div>
  )
}

export default ResultList