<div style="padding: 20px;">
  <h2>DevExtreme Angular App</h2>

  <div style="margin-bottom: 10px;">
    <dx-date-box
      [(value)]="selectedDate"
      displayFormat="yyyy-MM-dd"
      placeholder="Select Date">
    </dx-date-box>
  </div>

  <div style="margin-bottom: 10px;">
    <dx-number-box
      [(value)]="selectedNumber"
      placeholder="Enter Number">
    </dx-number-box>
  </div>

  <dx-button
    text="Submit"
    (onClick)="submitData()">
  </dx-button>
</div>
