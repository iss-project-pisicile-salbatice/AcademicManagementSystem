import React from 'react'
import ResultItem from './ResultItem'
const ResultList = ({results}) => {
  return (
    <div className="resultList">{results.map((result) => (<ResultItem result={result}/>))}</div>
  )
}

export default ResultList