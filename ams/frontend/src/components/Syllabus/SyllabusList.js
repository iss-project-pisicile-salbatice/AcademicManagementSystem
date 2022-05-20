import React from 'react'
import "./Syllabus.css"
import SyllabusItem from './SyllabusItem'


const SyllabusList = ({syllabusList}) => {
  return (
    <div class="syllabusContainer">{syllabusList.map((syllabusItem) => (
        <SyllabusItem syllabusItem={syllabusItem}/>
    ))}</div>
  )
}

export default SyllabusList