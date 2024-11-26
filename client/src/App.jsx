import React, { useState } from 'react'
import Header from './components/Header'
// import Graph from './components/GraphCanva'
import Graph from './components/Graph';
import { Toaster } from 'react-hot-toast'

export default function App() {

    return (
        <div className=''>

            <Header />
            <Graph/>
            <Toaster position='bottom-center' />
        </div>
    )
}
