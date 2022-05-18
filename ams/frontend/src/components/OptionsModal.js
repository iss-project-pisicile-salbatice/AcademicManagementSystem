import React, { useState } from "react";
import "./Components.css";

export default function OptionsModal(open) {
  if (!open.open) {
    return null;
  }
  return (
    <div className="options">
      <button className="optionsElems">
        <a href="/profilePage">
        Profile
        </a>
      </button>
      <button className="optionsElems">
        <a href="/login">
          <img
            className="logo"
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHoAAAB6CAMAAABHh7fWAAAAY1BMVEX///8AAAD39/c+Pj7z8/Pr6+tmZmbMzMybm5uYmJienp6Pj4+Tk5P8/Pzi4uLDw8MiIiIdHR0RERFwcHBPT082NjbU1NS1tbVJSUlVVVVhYWGnp6d+fn4nJyd3d3cvLy+GhobDYwSPAAACcElEQVRoge2a2WKDIBREJW5ZqiZmsWZp+v9f2aRJXBrgXlCZhzKvgifoYIA7QeDl5eXlNa6yw/J7m6hVTQWuEkHpNAV3tiC5N30qeufFwZpcrTlkEcrB9e1Safc2oi0LLMRV1jsuHxfnFuQ8ZJLFRda9fl1dmpN5D1s16qi9bDrumA2W2+zQuW7GjvhjFmIluUFKNVDqaEDexRRafPDJp/7Nk3mRx0plsjv00WLBJeebbrdzzv/NKjTb5+dOn01qAX5HM72Wl22PvexF2qB57GWnQ2RHlqBZXru0zW1eswrNmGO5SWMTNO3zqmm6tn3cCjTp81XT8suarEBTXmuXJcXoaGLc16ad/SJDida/72ZWXyzntBattW7TKLR3mQatm99TozVemxytZk+PVvrcAVrlcxdohdecoOVzjEZnM0oZiZZ6jUKnx5DWnkTL2AR6Tt+UqXef69HFaGSJz7XobDci+o2tRZtsiBha4dB/xu0U3fe5W3TP547R3XG7RnfYztGtz92jm/8xAPo1xxDop88h6Acbg/71OQh9Z6PQIsWhExy6/Jdo4APH2Qw3uXCfFNyHFPf3gfvTxC0VcAsk3LIQtxjGbQFwGx/cds9kkwvc2gMPNJDHOMjDqwB4ZMfSVAeVI6Bxx7O4Q2ncUTyuAIEru2iKTcASW1tYtK5e2xYWgeVUYBEZWDoHBgaAMQlkOAQYiQEGgZDxJ2DoyyzqVo8adTML+G0ptNnnARdrNApz7iXdB4Q5TSKs0vTsgAirQXBXmp4dEtwN2HFl2QN/xpWFdXicF9KuFb2HhLTvYkTTJ4vFk4F8Gx95eXl5eU2iH+FpK/2FOqh+AAAAAElFTkSuQmCC"
          />
          Sign Out
        </a>
      </button>
    </div>
  );
}
