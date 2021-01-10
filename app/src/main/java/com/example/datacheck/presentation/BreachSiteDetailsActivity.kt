package com.example.datacheck.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.datacheck.model.DataBreach
import com.example.datacheck.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_breach_site_details.*


class BreachSiteDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breach_site_details)

        var breach = intent.getParcelableExtra("breach") as DataBreach?

        Picasso.get().load(breach?.logoPath).into(breachLogo)
        breachTitle.text = breach?.title

        setTextViewHTML(breachDescription, breach?.description)



    }

    private fun makeLinkClickable(strBuilder: SpannableStringBuilder, span: URLSpan?) {
        val start = strBuilder.getSpanStart(span)
        val end = strBuilder.getSpanEnd(span)
        val flags = strBuilder.getSpanFlags(span)
        val clickable: ClickableSpan = object : ClickableSpan() {

            override fun onClick(widget: View) {

                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(span?.url))
                startActivity(browserIntent)
            }
        }
        strBuilder.setSpan(clickable, start, end, flags)
        strBuilder.removeSpan(span)
    }

    private fun setTextViewHTML(text: TextView, html: String?) {
        val sequence: CharSequence = Html.fromHtml(html)
        val strBuilder = SpannableStringBuilder(sequence)
        val urls = strBuilder.getSpans(0, sequence.length, URLSpan::class.java)
        for (span in urls) {
            makeLinkClickable(strBuilder, span)
        }
        text.text = strBuilder
        text.movementMethod = LinkMovementMethod.getInstance()
    }

}