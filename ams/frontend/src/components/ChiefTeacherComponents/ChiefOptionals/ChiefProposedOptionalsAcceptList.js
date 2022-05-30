import React, { useState, useEffect } from 'react';
import ProposedOptionalItem from './ProposedOptionalItem';

const ChiefProposedOptionalsAcceptList = ({optionalsDisciplines, toggleAccept}) => {
    return (
        <div className="optionalsContainer">{optionalsDisciplines.map((proposedOptional) => (
            <ProposedOptionalItem key={proposedOptional.optionalID} proposedOptional={proposedOptional} toggleAccept={toggleAccept}/>
        ))}</div>
    )
}

export default ChiefProposedOptionalsAcceptList;