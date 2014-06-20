/**
 * Copyright 2014  XCL-Charts
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 	
 * @Project XCL-Charts 
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version v0.1
 */
package org.xclcharts.renderer.plot;

import org.xclcharts.common.DrawHelper;

import android.graphics.Canvas;
import android.graphics.Paint.Align;

/**
 * @ClassName PlotTitleRender
 * @Description 标题绘制类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class PlotTitleRender extends PlotTitle{

	public PlotTitleRender()
	{
		super();		
	}
	
	/**
	 * 绘制标题
	 */
	public void renderTitle(float chartLeft,
							float chartRight,
							float chartTop,
							float chartWidth,
							float plotTop,
							 Canvas canvas)
	{
		
		DrawHelper dw = new DrawHelper();
				
		int titleHeight = 0;
		int subtitleHeight = 0;
		int totalHegith = 0;
		
		float titleInitY = 0.0f;
		
		float titleX = 0.0f;
		float titleY = 0.0f;
		
		float subtitleX = 0.0f;
		float subtitleY = 0.0f;
		
		if(this.getTitle().length() == 0 && getChartSubTitle().length() == 0) return;	

		if(getTitle().length() > 0 )
		{
			 titleHeight = dw.getPaintFontHeight(this.getTitlePaint());			
		}
		if(getTitle().length() > 0 )
		{
			subtitleHeight = dw.getPaintFontHeight(this.getSubtitlePaint());
		}			
		totalHegith = titleHeight + subtitleHeight;	
		float pcHeight = Math.abs(plotTop - chartTop) ;		
		
		//用来确定 titleY,需要Chart top的值
		switch(this.getTitlePosition())
		{
		case UP:
			
			titleInitY = chartTop + titleHeight;
			break;
		case CENTER:			
			titleInitY = Math.round(chartTop + pcHeight/2 - totalHegith/2);			
			break;
		case LOWER:
			titleInitY = plotTop - titleHeight;			
			break;
		}
		
		//
		
		switch(this.getTitleAlign())
		{
		case LEFT:
			titleX = chartLeft;
			titleY = titleInitY;
			
			subtitleX = chartLeft;
			subtitleY = titleY + subtitleHeight;
			
			getTitlePaint().setTextAlign(Align.LEFT);			
			getSubtitlePaint().setTextAlign(Align.LEFT);			
			break;
		case CENTER:
			
			titleX = (float)Math.round(chartLeft + chartWidth / 2);
			titleY = titleInitY;
			
			subtitleX = titleX;
			subtitleY = (float) (titleY + subtitleHeight);
			
			getTitlePaint().setTextAlign(Align.CENTER);			
			getSubtitlePaint().setTextAlign(Align.CENTER);	
			break;
		case RIGHT:
			
			titleX = chartRight;
			titleY = titleInitY;
			
			subtitleX = titleX;
			subtitleY = titleY + subtitleHeight;
			
			getTitlePaint().setTextAlign(Align.RIGHT);			
			getSubtitlePaint().setTextAlign(Align.RIGHT);			
						
			break;
		}
		if(getTitle().length() > 0 )
		{		
			canvas.drawText(
					getTitle(),titleX , titleY, this.getTitlePaint());
		}
		if(getChartSubTitle().length() > 0 )
		{
			canvas.drawText(
					getChartSubTitle(),subtitleX , subtitleY, this.getSubtitlePaint());
		}
	}
}