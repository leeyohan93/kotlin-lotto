package lotto

import lotto.domain.LottoShop
import lotto.domain.LottoTicket
import lotto.domain.WinningLotto
import lotto.dto.LottoTicketsResponse
import lotto.dto.WinningLottoResponse
import lotto.view.InputView
import lotto.view.ResultView

fun main() {
    val buyingRequest = InputView.buying()

    val lottoShop = LottoShop()
    val lottoTickets = lottoShop.buying(
        buyingAmount = buyingRequest.amount,
        manualNumbers = buyingRequest.manualNumbers
    )

    ResultView.printLottoTickets(LottoTicketsResponse.of(lottoTickets, buyingRequest.manualCount))

    val winningLottoRequest = InputView.winningLotto()

    val winningLotto = WinningLotto(
        winningTicket = LottoTicket(winningLottoRequest.winningLottoNumbers),
        bonusNumber = winningLottoRequest.bonusNumber
    )
    val lottoRanks = lottoTickets.matching(winningLotto)

    ResultView.printRank(WinningLottoResponse.of(lottoRanks.count(), lottoRanks.profitRate(lottoTickets.amount())))
}
